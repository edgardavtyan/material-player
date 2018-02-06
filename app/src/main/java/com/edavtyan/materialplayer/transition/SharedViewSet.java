package com.edavtyan.materialplayer.transition;

import android.view.View;

import lombok.Getter;
import lombok.Setter;

public class SharedViewSet {
	private @Getter final String transitionName;
	private @Getter final View normalView;
	private @Getter @Setter TransitionType transitionType;
	private @Getter @Setter View enterPortraitView;
	private @Getter @Setter View enterLandscapeView;
	private @Getter @Setter View exitPortraitView;
	private @Getter @Setter View exitLandscapeView;
	private @Getter int enterDuration;
	private @Getter int exitDuration;
	private @Getter int exitDelay;

	public static SharedViewSet translating(String transitionName, View normalView, View sharedView) {
		return new SharedViewSet(transitionName, normalView, sharedView);
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
}
