package com.edavtyan.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public abstract class SummaryPreference<TController extends SummaryController>
		extends DialogPreference<TController, SummaryEntry> {

	protected final String initialSummary;
	protected TextView titleView;


	public SummaryPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialSummary = controller.getSummary().toString();
		updateEntry();
	}

	public SummaryPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialSummary = controller.getSummary().toString();
		updateEntry();
	}


	public void updateEntry() {
		if (isInEditMode()) {
			entryView.setSummary(initialSummary.replace("%s", controller.getDefaultValue()));
			return;
		}

		entryView.setSummary(initialSummary.replace("%s", controller.getCurrentPreference()));
	}


	@Override
	protected int getEntryLayoutId() {
		return R.layout.entry_summary;
	}

	@Override
	protected SummaryEntry onCreateEntryView() {
		setOrientation(VERTICAL);
		return new SummaryEntry(this, controller);
	}
}
