package com.edavtyan.materialplayer.lib.transition;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class EnterFadeOutTransition extends EnterSharedTransition {

	@Override
	protected AnimatorSet buildAnimatorSet(TransitionData data) {
		View sharedView = data.getSharedView();
		sharedView.setAlpha(1);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(ObjectAnimator.ofFloat(sharedView, "alpha", 0f));
		animatorSet.setDuration(data.getDuration());
		return animatorSet;
	}

	@Override
	protected void setUpData(TransitionData data) {
		data.setStartScaleX(1);
		data.setStartScaleY(1);
		super.setUpData(data);
	}

	@Override
	protected boolean disableStartAction() {
		return true;
	}

	@Override
	protected boolean disableEndAction() {
		return true;
	}
}
