package com.edavtyan.materialplayer.transition;

import android.view.View;

import lombok.Getter;
import lombok.Setter;

public class SharedViewSet {
	private @Getter final String transitionName;
	private @Getter final View normalView;

	private @Getter @Setter View enterPortraitView;
	private @Getter @Setter View enterLandscapeView;
	private @Getter @Setter View exitPortraitView;
	private @Getter @Setter View exitLandscapeView;

	public SharedViewSet(View normalView, View sharedView, String transitionName) {
		this.normalView = normalView;
		this.transitionName = transitionName;
		enterPortraitView = sharedView;
		enterLandscapeView = sharedView;
		exitPortraitView = sharedView;
		exitLandscapeView = sharedView;
	}
}
