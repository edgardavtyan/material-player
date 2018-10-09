package com.edavtyan.materialplayer.lib.animation;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;

import lombok.Setter;

public class CircularRevealAnimation {
	private final View view;
	private final int x;
	private final int y;
	private final int startRadius;
	private final int endRadius;

	private @Setter int duration;

	public CircularRevealAnimation(View view, int x, int y, int startRadius, int endRadius) {
		this.view = view;
		this.x = x;
		this.y = y;
		this.startRadius = startRadius;
		this.endRadius = endRadius;
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void show() {
		view.setVisibility(View.VISIBLE);
		Animator anim = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
		anim.setDuration(duration);
		anim.start();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void hide() {
		Animator anim = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
		anim.setDuration(duration);
		anim.setInterpolator(new DecelerateReverseInterpolator(2));
		anim.addListener((AnimatorEndListener) () -> view.setVisibility(View.INVISIBLE));
		anim.start();
	}

	public static boolean isSupported() {
		return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
	}
}
