package com.edavtyan.prefs.color;

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

public class ColorSelectionEntry implements View.OnClickListener {
	@BindView(R2.id.title) TextView titleView;
	@BindView(R2.id.color) ColorCircleView colorView;

	private @Setter OnClickListener onClickListener;

	interface OnClickListener {
		void onEntryClick();
	}

	public ColorSelectionEntry(Context context, LinearLayout view) {
		LayoutInflater.from(context).inflate(R.layout.entry_color, view, true);
		ButterKnife.bind(this, view);
		view.setOnClickListener(this);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setColor(int color) {
		colorView.setColor(color);
	}

	@Override
	public void onClick(View v) {
		if (onClickListener != null) {
			onClickListener.onEntryClick();
		}
	}
}
