package com.edavtyan.materialplayer.transition;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ExitFadeOutTransition extends SharedTransition {
	@Override
	protected AnimatorSet buildAnimatorSet(TransitionData data) {
		View sharedView = data.getSharedView();
		sharedView.setVisibility(View.VISIBLE);

		data.getNormalView().setVisibility(View.INVISIBLE);

		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(ObjectAnimator.ofFloat(sharedView, "alpha", 1f));
		animatorSet.setStartDelay(data.getDelay());
		animatorSet.setDuration(data.getDuration());
		return animatorSet;
	}

	@Override
	protected boolean disableStartAction() {
		return true;
	}
}
