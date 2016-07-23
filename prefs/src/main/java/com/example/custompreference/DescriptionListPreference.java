package com.example.custompreference;

import android.content.Context;
import android.util.AttributeSet;

public class DescriptionListPreference
		extends ListPreference<DescriptionListController, DescriptionListAdapter> {

	public DescriptionListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DescriptionListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected DescriptionListAdapter createAdapter(DescriptionListController controller) {
		return new DescriptionListAdapter(context, controller);
	}

	@Override
	protected DescriptionListController createController(AttributeSet attrs) {
		return new DescriptionListController(this, attrs);
	}
}
