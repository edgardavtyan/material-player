package com.edavtyan.utils.advanced;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class GenericFrameLayout extends FrameLayout {
	public GenericFrameLayout(Context context) {
		super(context);
	}

	public GenericFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GenericFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@SuppressWarnings("unchecked")
	public <T> T findView(@IdRes int id) {
		return (T) findViewById(id);
	}
}
