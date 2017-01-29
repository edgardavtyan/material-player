package com.edavtyan.materialplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public final class WindowUtils {
	private WindowUtils() {
	}

	public static void makeStatusBarTransparent(Window window) {
		if (Build.VERSION.SDK_INT >= 21) {
			window.getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
					View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			window.setStatusBarColor(Color.TRANSPARENT);
		}
	}

	public static boolean isPortrait(Context context) {
		Configuration configuration = context.getResources().getConfiguration();
		return configuration.orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public static void makeStatusBarSemiTransparent(Activity activity) {
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	}
}
