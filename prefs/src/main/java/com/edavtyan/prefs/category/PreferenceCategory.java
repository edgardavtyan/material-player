package com.edavtyan.prefs.category;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreferenceCategory extends LinearLayout {
	@BindView(R2.id.heading) TextView headingView;

	public PreferenceCategory(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public PreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		inflate(context, R.layout.category, this);
		setOrientation(VERTICAL);

		ButterKnife.bind(this);

		PreferenceCategoryModel model = new PreferenceCategoryModel(context, attrs);
		headingView.setTypeface(Typeface.create("sans-serif-medium", 0));
		headingView.setTextColor(model.getColor());
		headingView.setText(model.getTitle());
	}
}
