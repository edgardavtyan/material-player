package com.edavtyan.materialplayer.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DpConverter {
	public static int convertDpToPixel(int dp) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		int px = (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}

	public static int convertPixelsToDp(int px) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		int dp = (int) (px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
		return dp;
	}

}
