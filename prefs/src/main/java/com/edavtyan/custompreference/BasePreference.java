package com.edavtyan.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.edavtyan.custompreference.utils.AttributeResolver;

public abstract class BasePreference<TController extends BaseController>
		extends LinearLayout {

	protected final Context context;
	protected final TController controller;
	protected final AttributeResolver attrs;


	public BasePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.controller = createController(attrs);
		this.attrs = new AttributeResolver(context);
		initCommonEntryViewProperties();
		createEntryView();
	}

	public BasePreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		this.controller = createController(attrs);
		this.attrs = new AttributeResolver(context);
		initCommonEntryViewProperties();
		createEntryView();
	}


	protected abstract TController createController(AttributeSet attrs);

	protected abstract void createEntryView();


	private void initCommonEntryViewProperties() {
		setBackgroundDrawable(attrs.getDrawable(R.attr.selectableItemBackground));
		setGravity(Gravity.CENTER_VERTICAL);

		int height = attrs.getDimen(R.attr.listPreferredItemHeight);
		setMinimumHeight(height);

		int paddingLeft = attrs.getDimen(R.attr.listPreferredItemPaddingLeft);
		int paddingRight = attrs.getDimen(R.attr.listPreferredItemPaddingRight);
		setPadding(paddingLeft, 0, paddingRight, 0);
	}
}
