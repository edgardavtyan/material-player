package com.edavtyan.materialplayer.transition;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.ed.libsutils.utils.WindowUtils;

import java.util.ArrayList;
import java.util.Stack;

public class SharedTransitionsManager {
	public static final String PARAM_X = ":x";
	public static final String PARAM_Y = ":y";
	public static final String PARAM_WIDTH = ":width";
	public static final String PARAM_HEIGHT = ":height";
	public static final String EXTRA_TRANSITION_NAMES = "transitionNames";

	private final Stack<SourceSharedViews> sourceViews;
	private final Stack<SharedViewSet[]> sharedViewSets;
	private final Stack<View[]> enterFadingViews;
	private final Stack<View[]> exitPortraitFadingViews;
	private final Stack<View[]> exitLandscapeFadingViews;

	public SharedTransitionsManager() {
		sourceViews = new Stack<>();
		sharedViewSets = new Stack<>();
		enterFadingViews = new Stack<>();
		exitPortraitFadingViews = new Stack<>();
		exitLandscapeFadingViews = new Stack<>();
	}

	public void setSharedViewSets(SharedViewSet... sharedViewSets) {
		this.sharedViewSets.push(sharedViewSets);
	}

	public void setEnterFadingViews(View... views) {
		enterFadingViews.push(views);
	}

	public void setExitPortraitFadingViews(View... views) {
		exitPortraitFadingViews.push(views);
	}

	public void setExitLandscapeFadingViews(View... views) {
		exitLandscapeFadingViews.push(views);
	}

	public void pushSharedViews(SourceSharedViews views) {
		sourceViews.push(views);
	}

	public void beginEnterTransition(Activity activity) {
		Intent intent = activity.getIntent();
		ArrayList<String> transitionNames = intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES);

		if (transitionNames == null) {
			return;
		}

		for (View view : enterFadingViews.pop()) {
			view.setAlpha(0);
			view.animate().alpha(1);
		}

		SharedViewSet[] lastSharedViewSets = sharedViewSets.peek();
		for (String transitionName : transitionNames) {
			SharedViewSet sharedViewSet = findSharedViewSet(lastSharedViewSets, transitionName);
			if (sharedViewSet == null) continue;

			View sharedView = WindowUtils.isPortrait(activity)
					? sharedViewSet.getEnterPortraitView()
					: sharedViewSet.getEnterLandscapeView();
			sharedView.post(() -> {
				View sourceSharedView = sourceViews.peek().find(transitionName);
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

	public void beginExitTransition(Activity activity) {
		Intent intent = activity.getIntent();
		ArrayList<String> transitionNames = intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES);

		if (sourceViews.isEmpty()) {
			activity.finish();
			return;
		}

		if (transitionNames == null) {
			activity.finish();
			sourceViews.pop().show();
			return;
		}

		if (WindowUtils.isPortrait(activity)) {
			for (View view : exitPortraitFadingViews.pop()) {
				view.animate().alpha(0);
			}
		} else {
			for (View view : exitLandscapeFadingViews.pop()) {
				view.animate().alpha(0);
			}
		}

		SourceSharedViews sourceSharedViews = sourceViews.pop();
		SharedViewSet[] lastSharedViewSets = sharedViewSets.pop();
		for (String transitionName : transitionNames) {
			View sourceSharedView = sourceSharedViews.find(transitionName);
			SharedViewSet sharedViewSet = findSharedViewSet(lastSharedViewSets, transitionName);

			if (sharedViewSet == null) continue;

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

	private SharedViewSet findSharedViewSet(SharedViewSet[] sharedViewSets, String transitionName) {
		for (SharedViewSet sharedViewSet : sharedViewSets) {
			if (sharedViewSet.getTransitionName().equals(transitionName)) {
				return sharedViewSet;
			}
		}

		return null;
	}
}
