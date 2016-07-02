package com.edavtyan.materialplayer.utils;

import android.content.Context;
import android.util.TypedValue;

import com.edavtyan.materialplayer.R;

public class AppColors {
	public final int primary;
	public final int primaryDark;
	public final int accent;
	public final int textPrimary;
	public final int textSecondary;
	public final int windowBackground;
	public final int divider;

	public AppColors(Context context) {
		primary = resolveAttribute(context, R.attr.colorPrimary);
		primaryDark = resolveAttribute(context, R.attr.colorPrimaryDark);
		accent = resolveAttribute(context, R.attr.colorAccent);
		textPrimary = resolveAttribute(context, R.attr.textColorContrastPrimary);
		textSecondary = resolveAttribute(context, R.attr.textColorSecondary);
		windowBackground = resolveAttribute(context, android.R.attr.windowBackground);
		divider = resolveAttribute(context, R.attr.dividerColor);
	}

	private static int resolveAttribute(Context context, int attrId) {
		TypedValue attr = new TypedValue();
		context.getTheme().resolveAttribute(attrId, attr, true);
		return attr.data;
	}
}
