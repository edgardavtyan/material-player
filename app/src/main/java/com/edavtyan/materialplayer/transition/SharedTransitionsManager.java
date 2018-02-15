package com.edavtyan.materialplayer.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.ed.libsutils.listeners.SelectableAnimatorListener;
import com.ed.libsutils.utils.WindowUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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

		Intent intent = activity.getIntent();
		ArrayList<String> transitionNames = intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES);

		if (transitionNames == null) {
			return;
		}

		Class activityClass = activity.getClass();
		for (View view : enterFadingViews.remove(activityClass)) {
			view.setAlpha(0);
			view.animate().alpha(1);
		}

		final int[] initTransitionsCount = {0};
		final int[] totalTransitionsCount = {transitionNames.size()};
		SharedViewSet[] lastSharedViewSets = sharedViewSets.get(activityClass);
		AnimatorSet transitionSet = new AnimatorSet();
		for (String transitionName : transitionNames) {
			SharedViewSet sharedViewSet = findSharedViewSet(lastSharedViewSets, transitionName);
			if (sharedViewSet == null) {
				totalTransitionsCount[0]--;
				continue;
			}

			View sharedView = WindowUtils.isPortrait(activity)
					? sharedViewSet.getEnterPortraitView()
					: sharedViewSet.getEnterLandscapeView();
			sharedView.post(() -> {
				View sourceSharedView = sourceViews.get(sourceActivityClasses.peek()).find(transitionName);
				TransitionData data = WindowUtils.isPortrait(activity)
						? sharedViewSet.buildEnterPortraitData(intent)
						: sharedViewSet.buildEnterLandscapeData(intent);
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
						.build(data);
				transitionSet.playTogether(transition);
				initTransitionsCount[0]++;
				if (initTransitionsCount[0] == totalTransitionsCount[0]) {
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

		Class activityClass = activity.getClass();
		Intent intent = activity.getIntent();
		ArrayList<String> transitionNames = intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES);
		if (transitionNames == null) {
			activity.finish();
			sourceViews.remove(activityClass).show();
			return;
		}

		if (WindowUtils.isPortrait(activity)) {
			for (View view : exitPortraitFadingViews.remove(activityClass)) {
				view.animate().alpha(0);
			}
		} else {
			for (View view : exitLandscapeFadingViews.remove(activityClass)) {
				view.animate().alpha(0);
			}
		}

		SourceSharedViews sourceSharedViews = sourceViews.remove(sourceActivityClasses.pop());
		SharedViewSet[] lastSharedViewSets = sharedViewSets.remove(activityClass);
		AnimatorSet transitionSet = new AnimatorSet();
		for (String transitionName : transitionNames) {
			View sourceSharedView = sourceSharedViews.find(transitionName);
			SharedViewSet sharedViewSet = findSharedViewSet(lastSharedViewSets, transitionName);

			if (sharedViewSet == null) continue;

			TransitionData data = WindowUtils.isPortrait(activity)
					? sharedViewSet.buildExitPortraitData(intent)
					: sharedViewSet.buildExitLandscapeData(intent);
			AnimatorSet transition = SharedTransitionFactory
					.getExitTransition(sharedViewSet.getTransitionType())
					.withStartAction(() -> {
						sourceSharedView.setVisibility(View.INVISIBLE);
					})
					.withEndAction(() -> {
						sourceSharedView.setVisibility(View.VISIBLE);
					})
					.build(data);
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

	private SharedViewSet findSharedViewSet(SharedViewSet[] sharedViewSets, String transitionName) {
		for (SharedViewSet sharedViewSet : sharedViewSets) {
			if (sharedViewSet.getTransitionName().equals(transitionName)) {
				return sharedViewSet;
			}
		}

		return null;
	}
}
