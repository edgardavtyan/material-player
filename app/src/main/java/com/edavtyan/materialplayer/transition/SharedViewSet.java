package com.edavtyan.materialplayer.transition;

import android.content.Intent;
import android.view.View;

import com.ed.libsutils.utils.ViewUtils;

import lombok.Getter;
import lombok.Setter;

import static com.edavtyan.materialplayer.transition.SharedViewsTransition.PARAM_HEIGHT;
import static com.edavtyan.materialplayer.transition.SharedViewsTransition.PARAM_WIDTH;
import static com.edavtyan.materialplayer.transition.SharedViewsTransition.PARAM_X;
import static com.edavtyan.materialplayer.transition.SharedViewsTransition.PARAM_Y;

public class SharedViewSet {
	private @Getter final String transitionName;
	private @Getter final View normalView;
	private @Getter @Setter TransitionType transitionType;
	private @Getter @Setter View enterPortraitView;
	private @Getter @Setter View enterLandscapeView;
	private @Getter View exitPortraitView;
	private @Getter @Setter View exitLandscapeView;
	private @Getter int enterDuration;
	private @Getter int exitDuration;
	private @Getter int exitDelay;

	public static SharedViewSet translating(String transitionName, View normalView, View sharedView) {
		SharedViewSet set = new SharedViewSet(transitionName, normalView, sharedView);
		set.setTransitionType(TransitionType.TRANSLATE);
		return set;
	}

	public static SharedViewSet fading(String transitionName, View view) {
		SharedViewSet set = new SharedViewSet(transitionName, view, view);
		set.setTransitionType(TransitionType.FADE_OUT);
		return set;
	}

	private SharedViewSet(String transitionName, View normalView, View sharedView) {
		this.transitionName = transitionName;
		this.normalView = normalView;
		enterPortraitView = sharedView;
		enterLandscapeView = sharedView;
		exitPortraitView = sharedView;
		exitLandscapeView = sharedView;
		enterDuration = 500;
		exitDuration = 500;
		exitDelay = 0;
	}

	public SharedViewSet enterDuration(int durationMs) {
		this.enterDuration = durationMs;
		return this;
	}

	public SharedViewSet exitDelay(int startDelayMs) {
		this.exitDelay = startDelayMs;
		return this;
	}

	public SharedViewSet exitDuration(int durationMs) {
		this.exitDuration = durationMs;
		return this;
	}

	public SharedViewSet exitPortraitView(View view) {
		exitPortraitView = view;
		return this;
	}

	public TransitionData buildExitPortraitData(Intent intent) {
		return buildExitData(intent, exitPortraitView);
	}

	public TransitionData buildExitLandscapeData(Intent intent) {
		return buildExitData(intent, exitLandscapeView);
	}

	public TransitionData buildEnterPortraitData(Intent intent) {
		return buildEnterData(intent, enterPortraitView);
	}

	public TransitionData buildEnterLandscapeData(Intent intent) {
		return buildEnterData(intent, enterLandscapeView);
	}

	private TransitionData buildEnterData(Intent intent, View sharedView) {
		float intentX = intent.getFloatExtra(transitionName + PARAM_X, 0);
		float intentY = intent.getFloatExtra(transitionName + PARAM_Y, 0);
		float intentWidth = intent.getIntExtra(transitionName + PARAM_WIDTH, 0);
		float intentHeight = intent.getIntExtra(transitionName + PARAM_HEIGHT, 0);
		int[] sharedViewLocation = ViewUtils.getLocationOnScreen(sharedView);
		int[] normalViewLocation = ViewUtils.getLocationOnScreen(normalView);

		TransitionData data = new TransitionData();
		data.setStartXDelta(intentX - sharedViewLocation[0]);
		data.setStartYDelta(intentY - sharedViewLocation[1]);
		data.setStartScaleX(intentWidth / normalView.getWidth());
		data.setStartScaleY(intentHeight / normalView.getHeight());
		data.setEndXDelta(normalViewLocation[0] - sharedViewLocation[0]);
		data.setEndYDelta(normalViewLocation[1] - sharedViewLocation[1]);
		data.setSharedView(sharedView);
		data.setNormalView(normalView);
		data.setDuration(enterDuration);

		return data;
	}

	private TransitionData buildExitData(Intent intent, View sharedView) {
		float intentX = intent.getFloatExtra(transitionName + PARAM_X, 0);
		float intentY = intent.getFloatExtra(transitionName + PARAM_Y, 0);
		float intentWidth = intent.getIntExtra(transitionName + PARAM_WIDTH, 0);
		float intentHeight = intent.getIntExtra(transitionName + PARAM_HEIGHT, 0);

		int[] normalViewLocation = ViewUtils.getLocationOnScreen(normalView);
		int[] sharedViewLocation = ViewUtils.getLocationOnScreen(sharedView);

		TransitionData data = new TransitionData();
		data.setStartXDelta(normalViewLocation[0] - sharedViewLocation[0]);
		data.setStartYDelta(normalViewLocation[1] - sharedViewLocation[1]);
		data.setStartScaleX(intentWidth / normalView.getWidth());
		data.setStartScaleY(intentHeight / normalView.getHeight());
		data.setEndXDelta(intentX - sharedViewLocation[0]);
		data.setEndYDelta(intentY - sharedViewLocation[1]);
		data.setSharedView(sharedView);
		data.setNormalView(normalView);
		data.setDuration(exitDuration);
		data.setDelay(exitDelay);

		return data;
	}
}
