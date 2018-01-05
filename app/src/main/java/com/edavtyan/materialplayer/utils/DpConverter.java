package com.edavtyan.materialplayer.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DpConverter {
	public static int convertDpToPixel(int dp) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		int px = (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}
}
