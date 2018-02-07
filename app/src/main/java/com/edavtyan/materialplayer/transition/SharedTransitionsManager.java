package com.edavtyan.materialplayer.transition;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.ed.libsutils.utils.WindowUtils;

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
		if (intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES) == null) {
			return;
		}

		for (View view : enterFadingViews) {
			view.setAlpha(0);
			view.animate().alpha(1);
		}

		for (String transitionName : intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES)) {
			SharedViewSet sharedViewSet = findSharedViewSetByTransitionName(transitionName);
			View sharedView = sharedViewSet.getEnterPortraitView();
			sharedView.post(() -> {
				SourceSharedViews sourceSharedViews = currentSharedViews.peek();
				TransitionData data = WindowUtils.isPortrait(activity)
						? sharedViewSet.buildEnterPortraitData(intent)
						: sharedViewSet.buildEnterLandscapeData(intent);
				SharedTransitionFactory
						.getEnterTransition(sharedViewSet.getTransitionType())
						.withStartAction(() -> {
							new Handler().postDelayed(() -> {
								sourceSharedViews.find(transitionName).setVisibility(View.INVISIBLE);
							}, 50);
						})
						.withEndAction(() -> {
							sourceSharedViews
									.find(transitionName).setVisibility(View.VISIBLE);
						})
						.start(data);
			});
		}
	}

	public void beginExitTransition() {
		SourceSharedViews sourceSharedViews = currentSharedViews.pop();

		if (intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES) == null) {
			activity.finish();
			sourceSharedViews.show();
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

		for (String transitionName : intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES)) {
			SharedViewSet sharedViewSet = findSharedViewSetByTransitionName(transitionName);
			TransitionData data = WindowUtils.isPortrait(activity)
					? sharedViewSet.buildExitPortraitData(intent)
					: sharedViewSet.buildExitLandscapeData(intent);
			SharedTransitionFactory
					.getExitTransition(sharedViewSet.getTransitionType())
					.withStartAction(() -> {
						sourceSharedViews.find(transitionName).setVisibility(View.INVISIBLE);
					})
					.withEndAction(() -> {
						sourceSharedViews.remove(transitionName).setVisibility(View.VISIBLE);
						activity.finish();
						activity.overridePendingTransition(0, 0);
					})
					.start(data);
		}
	}

	private SharedViewSet findSharedViewSetByTransitionName(String transitionName) {
		for (SharedViewSet sharedViewSet : sharedViewSets) {
			if (sharedViewSet.getTransitionName().equals(transitionName)) {
				return sharedViewSet;
			}
		}

		return null;
	}
}
