package com.example.custompreference;

import android.content.Context;
import android.util.AttributeSet;

public class DescriptionListPreference
		extends ListPreference<DescriptionListController, DescriptionListAdapter>
		implements DescriptionListAdapter.OnItemSelectedListener {
	public DescriptionListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DescriptionListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected DescriptionListAdapter createAdapter(DescriptionListController controller) {
		DescriptionListAdapter adapter = new DescriptionListAdapter(context, controller);
		adapter.setOnItemSelectedListener(this);
		return adapter;
	}

	@Override
	protected DescriptionListController createController(Context context, AttributeSet attrs) {
		return new DescriptionListController(context, attrs);
	}

	@Override
	public void onItemSelectedListener() {
		closeDialog();
	}
}
