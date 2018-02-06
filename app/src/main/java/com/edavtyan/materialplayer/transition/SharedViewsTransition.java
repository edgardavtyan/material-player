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

			float intentX = intent.getFloatExtra(transitionName + PARAM_X, 0);
			float intentY = intent.getFloatExtra(transitionName + PARAM_Y, 0);
			float intentWidth = intent.getIntExtra(transitionName + PARAM_WIDTH, 0);
			float intentHeight = intent.getIntExtra(transitionName + PARAM_HEIGHT, 0);

			View sharedView = sharedViewSet.getEnterPortraitView();
			View normalView = sharedViewSet.getNormalView();
			sharedView.setVisibility(View.VISIBLE);
			sharedView.post(() -> {
				int[] sharedViewLocation = ViewUtils.getLocationOnScreen(sharedView);
				int[] normalViewLocation = ViewUtils.getLocationOnScreen(normalView);
				float startXDelta = intentX - sharedViewLocation[0];
				float startYDelta = intentY - sharedViewLocation[1];
				float startScaleX = intentWidth / normalView.getWidth();
				float startScaleY = intentHeight / normalView.getHeight();
				float endXDelta = normalViewLocation[0] - sharedViewLocation[0];
				float endYDelta = normalViewLocation[1] - sharedViewLocation[1];

				if (sharedViewSet.getTransitionType() == TransitionType.FADE_OUT) {
					startScaleX = 1;
					startScaleY = 1;
				}

				ViewUtils.setSize(sharedView, normalView);
				sharedView.setTranslationX(startXDelta);
				sharedView.setTranslationY(startYDelta);
				sharedView.setScaleX(startScaleX);
				sharedView.setScaleY(startScaleY);
				sharedView.setPivotX(0);
				sharedView.setPivotY(0);

				if (sharedViewSet.getTransitionType() == TransitionType.FADE_OUT) {
					sharedView.setAlpha(1);
					sharedView.animate()
							  .alpha(0)
							  .setDuration(sharedViewSet.getEnterDuration());
					return;
				}

				sharedView.animate()
						  .translationX(endXDelta)
						  .translationY(endYDelta)
						  .scaleX(1)
						  .scaleY(1)
						  .setDuration(500)
						  .withStartAction(() -> new Handler().postDelayed(() -> {
							  currentSharedViews.peek().setVisibility(View.INVISIBLE);
						  }, 50))
						  .withEndAction(() -> {
							  currentSharedViews.peek().setVisibility(View.VISIBLE);
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
		if (intent.getStringArrayListExtra(EXTRA_TRANSITION_NAMES) == null) {
			activity.finish();
			currentSharedViews.pop().setVisibility(View.VISIBLE);
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

			View sharedViewPortrait = sharedViewSet.getExitPortraitView();
			View sharedViewLandscape = sharedViewSet.getExitLandscapeView();
			View normalView = sharedViewSet.getNormalView();

			float intentX = intent.getFloatExtra(transitionName + PARAM_X, 0);
			float intentY = intent.getFloatExtra(transitionName + PARAM_Y, 0);
			float intentWidth = intent.getIntExtra(transitionName + PARAM_WIDTH, 0);
			float intentHeight = intent.getIntExtra(transitionName + PARAM_HEIGHT, 0);

			normalView.setVisibility(View.INVISIBLE);

			View sharedView = WindowUtils.isPortrait(activity) ? sharedViewPortrait : sharedViewLandscape;
			int[] artViewLocation = ViewUtils.getLocationOnScreen(normalView);
			int[] transitionArtViewLocation = ViewUtils.getLocationOnScreen(sharedView);
			float startXDelta = artViewLocation[0] - transitionArtViewLocation[0];
			float startYDelta = artViewLocation[1] - transitionArtViewLocation[1];
			float startScaleX = intentWidth / normalView.getWidth();
			float startScaleY = intentHeight / normalView.getHeight();
			float endXDelta = intentX - transitionArtViewLocation[0];
			float endYDelta = intentY - transitionArtViewLocation[1];

			if (sharedViewSet.getTransitionType() == TransitionType.FADE_OUT) {
				sharedView.setVisibility(View.VISIBLE);
				sharedView.animate()
						  .setStartDelay(sharedViewSet.getExitDelay())
						  .setDuration(sharedViewSet.getExitDuration())
						  .alpha(1)
						  .withStartAction(exitTransitionStartAction())
						  .withEndAction(exitTransitionEndAction());
				continue;
			}

			ViewUtils.setSize(sharedView, normalView);
			sharedView.setVisibility(View.VISIBLE);
			sharedView.setTranslationX(startXDelta);
			sharedView.setTranslationY(startYDelta);
			sharedView.setPivotX(0);
			sharedView.setPivotY(0);
			sharedView.animate()
					  .translationX(endXDelta)
					  .translationY(endYDelta)
					  .scaleX(startScaleX)
					  .scaleY(startScaleY)
					  .setDuration(500)
					  .withStartAction(exitTransitionStartAction())
					  .withEndAction(exitTransitionEndAction());
		}
	}

	private Runnable exitTransitionStartAction() {
		return () -> currentSharedViews.peek().setVisibility(View.INVISIBLE);
	}

	private Runnable exitTransitionEndAction() {
		return () -> {
			activity.finish();
			activity.overridePendingTransition(0, 0);
			currentSharedViews.pop().setVisibility(View.VISIBLE);
		};
	}
}
