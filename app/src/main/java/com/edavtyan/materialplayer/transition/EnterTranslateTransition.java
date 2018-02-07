package com.edavtyan.materialplayer.transition;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class EnterTranslateTransition extends EnterSharedTransition {
	@Override
	protected AnimatorSet buildAnimatorSet(TransitionData data) {
		View sharedView = data.getSharedView();

		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(
				ObjectAnimator.ofFloat(sharedView, "translationX", data.getEndXDelta()),
				ObjectAnimator.ofFloat(sharedView, "translationY", data.getEndYDelta()),
				ObjectAnimator.ofFloat(sharedView, "scaleX", 1),
				ObjectAnimator.ofFloat(sharedView, "scaleY", 1));
		animatorSet.setDuration(data.getDuration());
		return animatorSet;
	}

	@Override
	protected void onAnimationEnd(TransitionData data) {
		data.getSharedView().setVisibility(View.INVISIBLE);
		data.getSharedView().setTranslationX(0);
		data.getSharedView().setTranslationY(0);
		data.getNormalView().setVisibility(View.VISIBLE);
	}
}
