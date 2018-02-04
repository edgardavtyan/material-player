package com.edavtyan.materialplayer.transition;

import android.view.View;

import lombok.Getter;
import lombok.Setter;

public class SharedViewSet {
	private @Getter final String transitionName;
	private @Getter final View normalView;
	private @Getter final TransitionType transitionType;
	private @Getter @Setter View enterPortraitView;
	private @Getter @Setter View enterLandscapeView;
	private @Getter @Setter View exitPortraitView;
	private @Getter @Setter View exitLandscapeView;

	public SharedViewSet(String transitionName, View normalView, View sharedView) {
		this(transitionName, normalView, sharedView, TransitionType.TRANSLATE);
	}

	public SharedViewSet(String transitionName, View normalView, View sharedView, TransitionType transitionType) {
		this.transitionName = transitionName;
		this.normalView = normalView;
		this.transitionType = transitionType;
		enterPortraitView = sharedView;
		enterLandscapeView = sharedView;
		exitPortraitView = sharedView;
		exitLandscapeView = sharedView;
	}
}
