package com.edavtyan.materialplayer.lib.animation;

import android.animation.TimeInterpolator;

public class DecelerateReverseInterpolator implements TimeInterpolator {
	private float factor;

	public DecelerateReverseInterpolator(float factor) {
		this.factor = factor;
	}

	public float getInterpolation(float input) {
		float result;
		if (factor == 1.0f) {
			result = 1.0f - (1.0f - input) * (1.0f - input);
		} else {
			result = (float) (1.0f - Math.pow((1.0f - input), 2 * factor));
		}
		return Math.abs(result - 1f);
	}
}
