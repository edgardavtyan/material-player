package com.edavtyan.prefs.color;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.edavtyan.prefs.R;

import lombok.Cleanup;
import lombok.Getter;

public class ColorCircleAttributes {
	private final @Getter int backgroundColor;

	public ColorCircleAttributes(Context context, AttributeSet attributeSet) {
		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.ColorCircleView);
		backgroundColor = attrs.getColor(R.styleable.ColorCircleView_cp_background, 0);
	}
}
