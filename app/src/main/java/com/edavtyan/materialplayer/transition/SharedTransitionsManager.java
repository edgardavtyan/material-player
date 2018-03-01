package com.edavtyan.materialplayer.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.ed.libsutils.listeners.SelectableAnimatorListener;
import com.ed.libsutils.utils.WindowUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedTransitionsManager {
	public static final String PARAM_X = ":x";
	public static final String PARAM_Y = ":y";
	public static final String PARAM_WIDTH = ":width";
	public static final String PARAM_HEIGHT = ":height";
	public static final String EXTRA_TRANSITION_NAMES = "transitionNames";

	private final HashMap<Class, SourceSharedViews> sourceViews;
	private final HashMap<Class, SharedViewSet[]> sharedViewSets;
	private final HashMap<Class, View[]> enterFadingViews;
	private final HashMap<Class, View[]> exitPortraitFadingViews;
	private final HashMap<Class, View[]> exitLandscapeFadingViews;
	private final Stack<Class> sourceActivityClasses;

	public SharedTransitionsManager() {
		sourceViews = new HashMap<>();
		sharedViewSets = new HashMap<>();
		enterFadingViews = new HashMap<>();
		exitPortraitFadingViews = new HashMap<>();
		exitLandscapeFadingViews = new HashMap<>();
		sourceActivityClasses = new Stack<>();
	}

	public void createSharedTransition(OutputSharedViews data) {
		Class activityClass = data.getEnterFadingViews()[0].getContext().getClass();
		sharedViewSets.put(activityClass, data.getSharedViewSets());
		enterFadingViews.put(activityClass, data.getEnterFadingViews());
		exitPortraitFadingViews.put(activityClass, data.getExitPortraitFadingViews());
		exitLandscapeFadingViews.put(activityClass, data.getExitLandscapeFadingViews());
	}

	public void updateSourceViews(SourceSharedViews views) {
		if (sourceViews.containsKey(views.getActivityClass())) {
			sourceViews.put(views.getActivityClass(), views);
			sourceActivityClasses.remove(views.getActivityClass());
			sourceActivityClasses.push(views.getActivityClass());
		}
	}

	public void pushSourceViews(SourceSharedViews views) {
		sourceViews.put(views.getActivityClass(), views);
		sourceActivityClasses.remove(views.getActivityClass());
		sourceActivityClasses.push(views.getActivityClass());
	}

	public void beginEnterTransition(Activity activity, Bundle savedInstanceState) {
		if (savedInstanceState != null) return;
		if (!activity.getIntent().hasExtra(EXTRA_TRANSITION_NAMES)) return;

		for (View view : enterFadingViews.remove(activity.getClass())) {
			view.setAlpha(0);
			view.animate().alpha(1);
		}

		AnimatorSet transitionSet = new AnimatorSet();
		transitionSet.addListener(new SelectableAnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				activity.getWindow().setFlags(
						WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
						WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
			}
		});

		ArrayList<String> transitionNames = activity
				.getIntent().getStringArrayListExtra(EXTRA_TRANSITION_NAMES);
		AtomicInteger createdTransitionsCount = new AtomicInteger(0);
		AtomicInteger totalTransitionsCount = new AtomicInteger(transitionNames.size());
		SharedViewSet[] lastSharedViewSets = sharedViewSets.get(activity.getClass());
		for (String transitionName : transitionNames) {
			SharedViewSet sharedViewSet = findSharedViewSet(lastSharedViewSets, transitionName);
			if (sharedViewSet == null) {
				totalTransitionsCount.decrementAndGet();
				continue;
			}

			sharedViewSet.getEnterView(activity).post(() -> {
				View sourceSharedView = sourceViews.get(sourceActivityClasses.peek()).find(transitionName);
				AnimatorSet transition = SharedTransitionFactory
						.getEnterTransition(sharedViewSet.getTransitionType())
						.withStartAction(() -> {
							new Handler().postDelayed(() -> {
								sourceSharedView.setVisibility(View.INVISIBLE);
							}, 50);
						})
						.withEndAction(() -> {
							sourceSharedView.setVisibility(View.VISIBLE);
						})
						.build(sharedViewSet.buildEnterData(activity));
				transitionSet.playTogether(transition);

				createdTransitionsCount.incrementAndGet();
				if (createdTransitionsCount.get() == totalTransitionsCount.get()) {
					transitionSet.start();
				}
			});
		}
	}

	public void beginExitTransition(Activity activity) {
		if (sourceViews.isEmpty()) {
			activity.finish();
			return;
		}

		if (!activity.getIntent().hasExtra(EXTRA_TRANSITION_NAMES)) {
			activity.finish();
			sourceViews.remove(activity.getClass()).show();
			return;
		}

		View[] fadingViews = WindowUtils.isPortrait(activity)
				? exitPortraitFadingViews.remove(activity.getClass())
				: exitLandscapeFadingViews.remove(activity.getClass());
		for (View view : fadingViews) {
			view.animate().alpha(0);
		}

		SourceSharedViews sourceSharedViews = sourceViews.remove(sourceActivityClasses.pop());
		SharedViewSet[] lastSharedViewSets = sharedViewSets.remove(activity.getClass());
		AnimatorSet transitionSet = new AnimatorSet();
		for (String transitionName : activity.getIntent().getStringArrayListExtra(EXTRA_TRANSITION_NAMES)) {
			SharedViewSet sharedViewSet = findSharedViewSet(lastSharedViewSets, transitionName);
			if (sharedViewSet == null) continue;

			View sourceSharedView = sourceSharedViews.find(transitionName);
			AnimatorSet transition = SharedTransitionFactory
					.getExitTransition(sharedViewSet.getTransitionType())
					.withStartAction(() -> {
						sourceSharedView.setVisibility(View.INVISIBLE);
					})
					.withEndAction(() -> {
						sourceSharedView.setVisibility(View.VISIBLE);
					})
					.build(sharedViewSet.buildExitData(activity));
			transitionSet.playTogether(transition);
		}

		transitionSet.addListener(new SelectableAnimatorListener() {
			@Override
			public void onAnimationEnd(Animator animation) {
				activity.finish();
				activity.overridePendingTransition(0, 0);
				sourceSharedViews.raiseOnExitTransitionEndListener();
			}
		});
		transitionSet.start();
	}

	@Nullable
	private SharedViewSet findSharedViewSet(SharedViewSet[] sharedViewSets, String transitionName) {
		for (SharedViewSet sharedViewSet : sharedViewSets) {
			if (sharedViewSet.getTransitionName().equals(transitionName)) {
				return sharedViewSet;
			}
		}

		return null;
	}
}
