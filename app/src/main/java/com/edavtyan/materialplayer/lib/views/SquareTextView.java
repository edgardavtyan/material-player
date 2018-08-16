package com.edavtyan.materialplayer.lib.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class SquareTextView extends AppCompatTextView {
	public SquareTextView(Context context) {
		super(context);
	}

	public SquareTextView(
			Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	@SuppressWarnings("SuspiciousNameCombination")
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
}
