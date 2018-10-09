package com.edavtyan.materialplayer.lib.animation;

import android.animation.Animator;

interface AnimatorEndListener extends Animator.AnimatorListener {
	@Override
	default void onAnimationStart(Animator animation) {};

	@Override
	default void onAnimationEnd(Animator animation) {
		onAnimationEnd();
	};

	@Override
	default void onAnimationCancel(Animator animation) {};

	@Override
	default void onAnimationRepeat(Animator animation) {};

	void onAnimationEnd();
}
