package com.edavtyan.custompreference;

public abstract class SummaryController<TPreference extends SummaryPreference>
		extends DialogController<TPreference> {

	public SummaryController(TPreference preference) {
		super(preference);
	}


	public abstract CharSequence getSummary();
}
