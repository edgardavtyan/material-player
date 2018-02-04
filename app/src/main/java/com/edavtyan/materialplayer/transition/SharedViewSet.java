package com.edavtyan.materialplayer.transition;

import android.view.View;

import lombok.Getter;
import lombok.Setter;

public class SharedViewSet {
	private @Getter final View normalView;
	private @Getter final TransitionType transitionType;
	private @Getter @Setter View enterPortraitView;
	private @Getter @Setter View enterLandscapeView;
	private @Getter @Setter View exitPortraitView;
	private @Getter @Setter View exitLandscapeView;

	public SharedViewSet(View normalView, View sharedView) {
		this(normalView, sharedView, TransitionType.TRANSLATE);
	}

	public SharedViewSet(View normalView, View sharedView, TransitionType transitionType) {
		this.normalView = normalView;
		this.transitionType = transitionType;
		enterPortraitView = sharedView;
		enterLandscapeView = sharedView;
		exitPortraitView = sharedView;
		exitLandscapeView = sharedView;
	}
}
