package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;

import lombok.Cleanup;
import lombok.Getter;

class TitledSeekbarAttributes {
	private static final int DEFAULT_TEXT_SIZE = 25;
	private static final int DEFAULT_PROGRESS = 30;
	private static final int DEFAULT_MAX = 100;
	private static final String DEFAULT_TEXT = "Title";

	private @Getter String text;
	private @Getter int textSize;
	private @Getter int textWidth;
	private @Getter int progress;
	private @Getter int max;

	@SuppressWarnings("ResourceType")
	public TitledSeekbarAttributes(Context context, AttributeSet attributeSet) {
		@Cleanup("recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.TitledSeekbar);
		text = attrs.getString(R.styleable.TitledSeekbar_ts_text);
		if (text == null) text = DEFAULT_TEXT;
		textSize = attrs.getDimensionPixelSize(
				R.styleable.TitledSeekbar_ts_textSize, DEFAULT_TEXT_SIZE);
		textWidth = attrs.getDimensionPixelSize(
				R.styleable.TitledSeekbar_ts_textWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
		progress = attrs.getInteger(
				R.styleable.TitledSeekbar_ts_progress, DEFAULT_PROGRESS);
		max = attrs.getInteger(
				R.styleable.TitledSeekbar_ts_max, DEFAULT_MAX);
	}
}