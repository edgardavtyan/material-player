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
		this.transitionType = transitionType;
		enterPortraitView = sharedView;
		enterLandscapeView = sharedView;
		exitPortraitView = sharedView;
		exitLandscapeView = sharedView;
	}
}
