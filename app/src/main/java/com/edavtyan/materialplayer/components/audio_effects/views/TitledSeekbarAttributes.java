package com.edavtyan.materialplayer.components.audio_effects.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;

import lombok.Getter;

class TitledSeekbarAttributes {
	private static final int DEFAULT_TEXT_SIZE = 25;
	private static final int DEFAULT_PROGRESS = 30;
	private static final int DEFAULT_MAX = 100;
	private static final String DEFAULT_TEXT = "Title";

	private @Getter String text;
	private final @Getter int textSize;
	private final @Getter int textWidth;
	private final @Getter int progress;
	private final @Getter int max;

	@SuppressWarnings("ResourceType")
	public TitledSeekbarAttributes(Context context, AttributeSet attributeSet) {
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.TitledSeekbar);
		text = attrs.getString(R.styleable.TitledSeekbar_ts_text);
		text = (text == null) ? DEFAULT_TEXT : text;
		textSize = attrs.getDimensionPixelSize(R.styleable.TitledSeekbar_ts_textSize, DEFAULT_TEXT_SIZE);
		textWidth = attrs.getDimensionPixelSize(R.styleable.TitledSeekbar_ts_textWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
		progress = attrs.getInteger(R.styleable.TitledSeekbar_ts_progress, DEFAULT_PROGRESS);
		max = attrs.getInteger(R.styleable.TitledSeekbar_ts_max, DEFAULT_MAX);
		attrs.recycle();
	}
}
