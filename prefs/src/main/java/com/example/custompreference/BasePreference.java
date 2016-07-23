package com.example.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class BasePreference<TController extends BaseController>
		extends LinearLayout {

	protected final Context context;
	protected final TController controller;


	public BasePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.controller = createController(attrs);
		createEntryView();
	}

	public BasePreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		this.controller = createController(attrs);
		createEntryView();
	}


	protected abstract TController createController(AttributeSet attrs);
	protected abstract void createEntryView();
}
