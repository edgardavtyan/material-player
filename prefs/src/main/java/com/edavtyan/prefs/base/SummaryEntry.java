package com.edavtyan.prefs.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;

public class SummaryEntry implements View.OnClickListener {
	@BindView(R2.id.title) TextView titleView;
	@BindView(R2.id.summary) TextView summaryView;

	private @Setter OnClickListener onClickListener;

	public interface OnClickListener {
		void onEntryClick();
	}

	public SummaryEntry(Context context, LinearLayout view) {
		LayoutInflater.from(context).inflate(R.layout.entry_summary, view, true);
		ButterKnife.bind(this, view);
		view.setOrientation(LinearLayout.VERTICAL);
		view.setOnClickListener(this);
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
