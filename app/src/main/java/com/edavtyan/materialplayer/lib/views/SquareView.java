package com.edavtyan.materialplayer.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareView extends ImageView {
	public SquareView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = getMeasuredWidth();
		setMeasuredDimension(width, width);
	}
}
