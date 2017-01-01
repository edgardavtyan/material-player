package com.edavtyan.custompreference.category;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.custompreference.R;
import com.edavtyan.custompreference.utils.AttributeResolver;

public class PreferenceCategory extends LinearLayout {
	private final Context context;
	private final PreferenceCategoryModel controller;

	public PreferenceCategory(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.controller = new PreferenceCategoryModel(context, attrs);
		init();
	}

	public PreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		this.controller = new PreferenceCategoryModel(context, attrs);
		init();
	}

	private void init() {
		inflate(context, R.layout.category, this);

		setOrientation(VERTICAL);

		AttributeResolver res = new AttributeResolver(context);

		TextView headingView = (TextView) findViewById(R.id.heading);
		headingView.setTypeface(Typeface.create("sans-serif-medium", 0));
		headingView.setTextColor(controller.getColor());
		headingView.setText(controller.getTitle());
	}
}
