package com.edavtyan.materialplayer.utils;

import android.content.Context;
import android.util.TypedValue;

import com.edavtyan.materialplayer.R;

public class AppColors {
	public final int textPrimary;

	public AppColors(Context context) {
		textPrimary = resolveAttribute(context, R.attr.textColorContrastPrimary);
	}

	private static int resolveAttribute(Context context, int attrId) {
		TypedValue attr = new TypedValue();
		context.getTheme().resolveAttribute(attrId, attr, true);
		return attr.data;
	}
}
