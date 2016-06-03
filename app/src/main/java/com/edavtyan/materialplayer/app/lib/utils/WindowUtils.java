package com.edavtyan.materialplayer.app.lib.utils;

import android.graphics.Color;
import android.os.Build;
import android.view.Window;

public final class WindowUtils {
	private WindowUtils() {}

	public static void makeStatusBarTransparent(Window window) {
		if (Build.VERSION.SDK_INT >= 21) {
			window.getDecorView().setSystemUiVisibility(1280);
			window.setStatusBarColor(Color.TRANSPARENT);
		}
	}
}
