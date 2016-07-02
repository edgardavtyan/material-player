package com.edavtyan.materialplayer.utils;

public final class ColorUtils {
	private ColorUtils() {}

	public static float intToFloatAlpha(int intAlpha) {
		if (intAlpha > 255) intAlpha = 255;
		return intAlpha / 255f;
	}
}
