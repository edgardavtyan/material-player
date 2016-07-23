package com.example.custompreference;

import android.content.Context;
import android.util.AttributeSet;

public class SimpleListPreference extends ListPreference<SimpleListController, SimpleListAdapter>
		implements SimpleListAdapter.OnItemSelectedListener {

	public SimpleListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SimpleListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected SimpleListAdapter createAdapter(SimpleListController controller) {
		SimpleListAdapter adapter = new SimpleListAdapter(context, controller);
		adapter.setOnItemSelectedListener(this);
		return adapter;
	}

	@Override
	protected SimpleListController createController(Context context, AttributeSet attrs) {
		return new SimpleListController(context, attrs);
	}

	@Override
	public void onItemSelectedListener(CharSequence value) {
		closeDialog();
	}
}
