package com.edavtyan.materialplayer.lib.transition;

import android.view.View;

import lombok.Data;

@Data
public class TransitionData {
	private int duration;
	private int delay;
	private float startXDelta;
	private float startYDelta;
	private float startScaleX;
	private float startScaleY;
	private float endXDelta;
	private float endYDelta;
	private View sharedView;
	private View normalView;
}
