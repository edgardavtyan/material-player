package com.edavtyan.materialplayer.transition;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.ed.libsutils.utils.ViewUtils;
import com.ed.libsutils.utils.WindowUtils;

import java.util.ArrayList;

public class SharedViewsTransition {
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

	public SharedViewsTransition(Activity activity, CurrentSharedViews currentSharedViews) {
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

		ArrayList<String> transitionNames = intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES);
		for (int i = 0; i < transitionNames.size(); i++) {
			String transitionName = transitionNames.get(i);
			SharedViewSet sharedViewSet = findSharedViewSetByTransitionName(transitionName);

			sharedViewSet.getNormalView().setVisibility(View.INVISIBLE);

			View sharedView = sharedViewSet.getEnterPortraitView();
			View normalView = sharedViewSet.getNormalView();
			sharedView.setVisibility(View.VISIBLE);
			sharedView.post(() -> {
				TransitionData startData = sharedViewSet.buildStartData(intent);

				if (sharedViewSet.getTransitionType() == TransitionType.FADE_OUT) {
					startData.setStartScaleX(1);
					startData.setStartScaleY(1);
				}

				ViewUtils.setSize(sharedView, normalView);
				sharedView.setTranslationX(startData.getStartXDelta());
				sharedView.setTranslationY(startData.getStartYDelta());
				sharedView.setScaleX(startData.getStartScaleX());
				sharedView.setScaleY(startData.getStartScaleY());
				sharedView.setPivotX(0);
				sharedView.setPivotY(0);

				if (sharedViewSet.getTransitionType() == TransitionType.FADE_OUT) {
					sharedView.setAlpha(1);
					sharedView.animate()
							  .alpha(0)
							  .setDuration(sharedViewSet.getEnterDuration());
					return;
				}

				SourceSharedViews sourceSharedViews = currentSharedViews.peek();
				sharedView.animate()
						  .translationX(startData.getEndXDelta())
						  .translationY(startData.getEndYDelta())
						  .scaleX(1)
						  .scaleY(1)
						  .setDuration(sharedViewSet.getEnterDuration())
						  .withStartAction(() -> new Handler().postDelayed(() -> {
							  sourceSharedViews.find(transitionName).setVisibility(View.INVISIBLE);
						  }, 50))
						  .withEndAction(() -> {
							  sourceSharedViews.find(transitionName).setVisibility(View.VISIBLE);
							  sharedView.setVisibility(View.INVISIBLE);
							  sharedView.setTranslationX(0);
							  sharedView.setTranslationY(0);
							  normalView.setVisibility(View.VISIBLE);
						  })
						  .start();
			});
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

		ArrayList<String> transitionNames = intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES);
		for (int i = 0; i < transitionNames.size(); i++) {
			String transitionName = transitionNames.get(i);
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
}
