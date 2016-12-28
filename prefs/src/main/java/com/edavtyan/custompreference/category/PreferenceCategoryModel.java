package com.edavtyan.custompreference.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.edavtyan.custompreference.R;

import lombok.Cleanup;
import lombok.Getter;

public class PreferenceCategoryModel {
	private final @Getter String title;

	public PreferenceCategoryModel(Context context, AttributeSet attributeSet) {
		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceCategory);
		title = attrs.getString(R.styleable.PreferenceCategory_cp_title);
	}
}
