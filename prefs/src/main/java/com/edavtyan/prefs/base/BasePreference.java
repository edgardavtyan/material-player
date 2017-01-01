package com.edavtyan.prefs.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.utils.AttributeResolver;

public abstract class BasePreference extends LinearLayout {
	protected final Context context;

	public BasePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initCommonEntryViewProperties();
	}

	public BasePreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initCommonEntryViewProperties();
	}

	private void initCommonEntryViewProperties() {
		if (isInEditMode()) return;

		AttributeResolver attrs = new AttributeResolver(context);

		setBackgroundDrawable(attrs.getDrawable(R.attr.selectableItemBackground));
		setGravity(Gravity.CENTER_VERTICAL);

		int height = attrs.getDimen(R.attr.listPreferredItemHeight);
		setMinimumHeight(height);

		int paddingLeft = attrs.getDimen(R.attr.listPreferredItemPaddingLeft);
		int paddingRight = attrs.getDimen(R.attr.listPreferredItemPaddingRight);
		setPadding(paddingLeft, 0, paddingRight, 0);
	}
}
