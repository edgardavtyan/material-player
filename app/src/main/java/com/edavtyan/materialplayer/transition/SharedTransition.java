package com.edavtyan.materialplayer.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;

import com.ed.libsutils.listeners.SelectableAnimatorListener;

public abstract class SharedTransition {
	private Runnable startAction;
	private Runnable endAction;

	public void start(TransitionData data) {
		setUpData(data);
		AnimatorSet animatorSet = buildAnimatorSet(data);
		animatorSet.addListener(new SelectableAnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				if (!disableStartAction()) {
					SharedTransition.this.onAnimationStart(data);
					startAction.run();
				}
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (!disableEndAction()) {
					SharedTransition.this.onAnimationEnd(data);
					endAction.run();
				}
			}
		});
		animatorSet.start();
	}

	public SharedTransition withStartAction(Runnable startAction) {
		this.startAction = startAction;
		return this;
	}

	public SharedTransition withEndAction(Runnable endAction) {
		this.endAction = endAction;
		return this;
	}

	protected abstract AnimatorSet buildAnimatorSet(TransitionData data);

	protected boolean disableStartAction() {
		return false;
	}

	protected boolean disableEndAction() {
		return false;
	}

	protected void setUpData(TransitionData data) {
	}

	protected void onAnimationStart(TransitionData data) {

	}

	protected void onAnimationEnd(TransitionData data) {
	}
}
