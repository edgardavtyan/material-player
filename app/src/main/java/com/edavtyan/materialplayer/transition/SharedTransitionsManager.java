package com.edavtyan.materialplayer.transition;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.transition.exceptions.ShareViewNotFoundException;

import java.util.ArrayList;

public class SharedTransitionsManager {
	public static final String PARAM_X = ":x";
	public static final String PARAM_Y = ":y";
	public static final String PARAM_WIDTH = ":width";
	public static final String PARAM_HEIGHT = ":height";
	public static final String EXTRA_TRANSITION_NAMES = "transitionNames";

	private final Intent intent;
	private final Activity activity;
	private final CurrentSharedViews currentSharedViews;

	private SharedViewSet[] sharedViewSets;
	private View[] enterFadingViews;
	private View[] exitPortraitFadingViews;
	private View[] exitLandscapeFadingViews;

	public SharedTransitionsManager(Activity activity, CurrentSharedViews currentSharedViews) {
		this.activity = activity;
		this.currentSharedViews = currentSharedViews;
		this.intent = activity.getIntent();
	}

	public void setSharedViewSets(SharedViewSet... sharedViewSets) {
		this.sharedViewSets = sharedViewSets;
	}

	public void setEnterFadingViews(View... views) {
		enterFadingViews = views;
	}

	public void setExitPortraitFadingViews(View... views) {
		exitPortraitFadingViews = views;
	}

	public void setExitLandscapeFadingViews(View... views) {
		exitLandscapeFadingViews = views;
	}

	public void beginEnterTransition() {
		ArrayList<String> transitionNames = intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES);

		if (transitionNames == null) {
			return;
		}

		for (View view : enterFadingViews) {
			view.setAlpha(0);
			view.animate().alpha(1);
		}

		for (String transitionName : transitionNames) {
			SharedViewSet sharedViewSet = findSharedViewSet(transitionName);
			View sharedView = WindowUtils.isPortrait(activity)
					? sharedViewSet.getEnterPortraitView()
					: sharedViewSet.getEnterLandscapeView();
			sharedView.post(() -> {
				View sourceSharedView = currentSharedViews.peek().find(transitionName);
				TransitionData data = WindowUtils.isPortrait(activity)
						? sharedViewSet.buildEnterPortraitData(intent)
						: sharedViewSet.buildEnterLandscapeData(intent);
				SharedTransitionFactory
						.getEnterTransition(sharedViewSet.getTransitionType())
						.withStartAction(() -> {
							new Handler().postDelayed(() -> {
								sourceSharedView.setVisibility(View.INVISIBLE);
							}, 50);
						})
						.withEndAction(() -> {
							sourceSharedView.setVisibility(View.VISIBLE);
						})
						.start(data);
			});
		}
	}

	public void beginExitTransition() {
		ArrayList<String> transitionNames = intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES);

		if (currentSharedViews.isEmpty()) {
			activity.finish();
			return;
		}

		if (transitionNames == null) {
			activity.finish();
			currentSharedViews.pop().show();
			return;
		}

		if (WindowUtils.isPortrait(activity)) {
			for (View view : exitPortraitFadingViews) {
				view.animate().alpha(0);
			}
		} else {
			for (View view : exitLandscapeFadingViews) {
				view.animate().alpha(0);
			}
		}

		SourceSharedViews sourceSharedViews = currentSharedViews.pop();
		for (String transitionName : transitionNames) {
			View sourceSharedView = sourceSharedViews.find(transitionName);
			SharedViewSet sharedViewSet = findSharedViewSet(transitionName);
			TransitionData data = WindowUtils.isPortrait(activity)
					? sharedViewSet.buildExitPortraitData(intent)
					: sharedViewSet.buildExitLandscapeData(intent);
			SharedTransitionFactory
					.getExitTransition(sharedViewSet.getTransitionType())
					.withStartAction(() -> {
						sourceSharedView.setVisibility(View.INVISIBLE);
					})
					.withEndAction(() -> {
						sourceSharedView.setVisibility(View.VISIBLE);
						activity.finish();
						activity.overridePendingTransition(0, 0);
					})
					.start(data);
		}
	}

	private SharedViewSet findSharedViewSet(String transitionName) {
		for (SharedViewSet sharedViewSet : sharedViewSets) {
			if (sharedViewSet.getTransitionName().equals(transitionName)) {
				return sharedViewSet;
			}
		}

		throw new ShareViewNotFoundException(transitionName);
	}
}
