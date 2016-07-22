package com.example.custompreference;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.custompreference.utils.AttributeResolver;
import com.example.custompreference.utils.PixelConverter;

public class PreferenceCategory extends LinearLayout {
	private final Context context;
	private final PreferenceCategoryController controller;

	public PreferenceCategory(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.controller = new PreferenceCategoryController(context, attrs);
		init();
	}

	public PreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		this.controller = new PreferenceCategoryController(context, attrs);
		init();
	}

	private void init() {
		inflate(context, R.layout.category, this);

		setOrientation(VERTICAL);

		AttributeResolver res = new AttributeResolver(context);

		TextView headingView = (TextView) findViewById(R.id.heading);
		headingView.setTypeface(Typeface.create("sans-serif-medium", 0));
		headingView.setTextColor(res.getColor(R.attr.colorPrimary));
		headingView.setText(controller.getTitle());

		View view = new View(context, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, PixelConverter.dpToPx(1)));
		view.setBackgroundColor(Color.parseColor("#cccccc"));
	}
}
