package com.edavtyan.prefs.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import com.edavtyan.prefs.R;

import lombok.Cleanup;
import lombok.Getter;

public class PreferenceCategoryModel {
	private final @Getter String title;
	private final @Getter int color;

	public PreferenceCategoryModel(Context context, AttributeSet attributeSet) {
		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceCategory);
		title = attrs.getString(R.styleable.PreferenceCategory_cp_title);
		color = attrs.getColor(R.styleable.PreferenceCategory_cp_color, Color.RED);
	}
}
