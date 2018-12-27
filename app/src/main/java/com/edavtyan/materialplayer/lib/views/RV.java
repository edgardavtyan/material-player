package com.edavtyan.materialplayer.lib.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class RV extends RecyclerView {
	public RV(Context context) {
		super(context);
	}

	public RV(
			Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public RV(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return false;
	}
}
