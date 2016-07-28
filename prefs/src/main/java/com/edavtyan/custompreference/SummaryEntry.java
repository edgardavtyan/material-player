package com.edavtyan.custompreference;

import android.view.View;
import android.widget.TextView;

public class SummaryEntry {
	private final TextView titleView;
	private final TextView summaryView;


	public SummaryEntry(View view, SummaryController controller) {
		titleView = (TextView) view.findViewById(R.id.title);
		titleView.setText(controller.getTitle());

		summaryView = (TextView) view.findViewById(R.id.summary);
		summaryView.setText(controller.getSummary());
	}


	public void setTitle(CharSequence title) {
		titleView.setText(title);
	}

	public void setSummary(CharSequence summary) {
		summaryView.setText(summary);
	}
}
