package com.edavtyan.materialplayer.utils;

import android.content.Context;
import android.util.TypedValue;

import com.edavtyan.materialplayer.R;

public class AppColors {
	public final int primary;
	public final int primaryDark;
	public final int textPrimary;

	public AppColors(Context context) {
		primary = resolveAttribute(context, R.attr.colorPrimary);
		primaryDark = resolveAttribute(context, R.attr.colorPrimaryDark);
		textPrimary = resolveAttribute(context, R.attr.textColorContrastPrimary);
	}

	private static int resolveAttribute(Context context, int attrId) {
		TypedValue attr = new TypedValue();
		context.getTheme().resolveAttribute(attrId, attr, true);
		return attr.data;
	}
}
