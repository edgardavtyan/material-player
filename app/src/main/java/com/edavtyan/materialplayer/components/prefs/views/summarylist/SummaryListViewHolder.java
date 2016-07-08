package com.edavtyan.materialplayer.components.prefs.views.summarylist;

import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import lombok.Setter;

class SummaryListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
	private final View itemView;
	private final TextView titleView;
	private final TextView summaryView;
	private final AppCompatRadioButton radioButton;

	private @Setter OnHolderClickListener onHolderClickListener;

	//---

	interface OnHolderClickListener {
		void onHolderClick(SummaryListViewHolder holder);
	}

	//---

	public SummaryListViewHolder(View itemView) {
		super(itemView);
		this.itemView = itemView;
		itemView.setOnClickListener(this);
		titleView = (TextView) itemView.findViewById(R.id.title);
		summaryView = (TextView) itemView.findViewById(R.id.summary);
		radioButton = (AppCompatRadioButton) itemView.findViewById(R.id.radioButton);
	}

	//---

	public void setTitle(CharSequence title) {
		titleView.setText(title);
	}

	public void setSummary(CharSequence summary) {
		summaryView.setText(summary);
	}

	public void setChecked(boolean checked) {
		radioButton.setChecked(checked);
	}

	public void setOnClickListener(View.OnClickListener listener) {
		itemView.setOnClickListener(listener);
	}

	//---

	@Override
	public void onClick(View view) {
		if (onHolderClickListener != null) {
			onHolderClickListener.onHolderClick(this);
		}
	}
}
