package com.edavtyan.custompreference.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class PixelConverter {
	public static int dpToPx(float dp) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return Math.round(px);
	}
}
