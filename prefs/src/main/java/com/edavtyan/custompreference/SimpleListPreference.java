package com.edavtyan.custompreference;

import android.content.Context;
import android.util.AttributeSet;

public class SimpleListPreference extends ListPreference<SimpleListController, SimpleListAdapter> {

	public SimpleListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SimpleListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected SimpleListAdapter createAdapter(SimpleListController controller) {
		return new SimpleListAdapter(context, controller);
	}

	@Override
	protected SimpleListController createController(AttributeSet attrs) {
		return new SimpleListController(this, attrs);
	}
}
