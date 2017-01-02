package com.edavtyan.utils.generic;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class GenericLinearLayout extends LinearLayout {
	public GenericLinearLayout(Context context) {
		super(context);
	}

	public GenericLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GenericLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@SuppressWarnings("unchecked")
	public <T> T findView(@IdRes int id) {
		return (T) findViewById(id);
	}
}
