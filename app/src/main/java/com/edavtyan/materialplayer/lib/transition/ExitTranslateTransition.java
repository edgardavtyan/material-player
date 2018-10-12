package com.edavtyan.materialplayer.lib.transition;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;

import com.ed.libsutils.utils.ViewUtils;

public class ExitTranslateTransition extends SharedTransition {
	@Override
	protected AnimatorSet buildAnimatorSet(TransitionData data) {
		View normalView = data.getNormalView();
		normalView.setVisibility(View.INVISIBLE);

		View sharedView = data.getSharedView();
		ViewUtils.setSize(sharedView, data.getNormalView());
		sharedView.setVisibility(View.VISIBLE);
		sharedView.setTranslationX(data.getStartXDelta());
		sharedView.setTranslationY(data.getStartYDelta());
		sharedView.setPivotX(0);
		sharedView.setPivotY(0);

		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setInterpolator(new FastOutSlowInInterpolator());
		animatorSet.playTogether(
				ObjectAnimator.ofFloat(sharedView, "translationX", data.getEndXDelta()),
				ObjectAnimator.ofFloat(sharedView, "translationY", data.getEndYDelta()),
				ObjectAnimator.ofFloat(sharedView, "scaleX", data.getStartScaleX()),
				ObjectAnimator.ofFloat(sharedView, "scaleY", data.getStartScaleY()));
		animatorSet.setDuration(data.getDuration());

		return animatorSet;
	}
}
