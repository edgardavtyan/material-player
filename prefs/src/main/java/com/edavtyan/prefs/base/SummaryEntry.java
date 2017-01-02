package com.edavtyan.prefs.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.prefs.R;
import com.edavtyan.utils.generic.GenericLinearLayout;

import lombok.Setter;

public class SummaryEntry implements View.OnClickListener {
	private final TextView titleView;
	private final TextView summaryView;
	private @Setter OnClickListener onClickListener;

	public interface OnClickListener {
		void onEntryClick();
	}

	public SummaryEntry(Context context, GenericLinearLayout view) {
		LayoutInflater.from(context).inflate(R.layout.entry_summary, view, true);
		view.setOrientation(LinearLayout.VERTICAL);
		view.setOnClickListener(this);
		titleView = view.findView(R.id.title);
		summaryView = view.findView(R.id.summary);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setSummary(String summary, String currentPref) {
		summaryView.setText(summary.replace("%s", currentPref));
	}

	@Override
	public void onClick(View v) {
		if (onClickListener != null) {
			onClickListener.onEntryClick();
		}
	}
}
