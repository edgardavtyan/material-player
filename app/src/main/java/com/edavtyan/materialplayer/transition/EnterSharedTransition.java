package com.edavtyan.materialplayer.transition;

import android.view.View;

import com.ed.libsutils.utils.ViewUtils;

public abstract class EnterSharedTransition extends SharedTransition {
	@Override
	protected void setUpData(TransitionData data) {
		View normalView = data.getNormalView();
		normalView.setVisibility(View.INVISIBLE);

		View sharedView = data.getSharedView();
		ViewUtils.setSize(sharedView, normalView);
		sharedView.setVisibility(View.VISIBLE);
		sharedView.setTranslationX(data.getStartXDelta());
		sharedView.setTranslationY(data.getStartYDelta());
		sharedView.setScaleX(data.getStartScaleX());
		sharedView.setScaleY(data.getStartScaleY());
		sharedView.setPivotX(0);
		sharedView.setPivotY(0);
	}
}
