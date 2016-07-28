package com.edavtyan.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.edavtyan.custompreference.utils.AttributeResolver;

public abstract class BasePreference<TController extends BaseController, TEntryView>
		extends LinearLayout {

	protected final Context context;
	protected final TController controller;
	protected final TEntryView entryView;
	protected final AttributeResolver attrs;


	public BasePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, getEntryLayoutId(), this);
		this.context = context;
		this.controller = createController(attrs);
		this.attrs = new AttributeResolver(context);
		this.entryView = onCreateEntryView();
		initCommonEntryViewProperties();
	}

	public BasePreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		inflate(context, getEntryLayoutId(), this);
		this.context = context;
		this.controller = createController(attrs);
		this.attrs = new AttributeResolver(context);
		this.entryView = onCreateEntryView();
		initCommonEntryViewProperties();
	}


	protected abstract TController createController(AttributeSet attrs);

	protected abstract TEntryView onCreateEntryView();

	protected abstract int getEntryLayoutId();


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
