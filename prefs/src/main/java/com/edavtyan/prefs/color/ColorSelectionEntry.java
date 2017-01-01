package com.edavtyan.prefs.color;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.prefs.R;

import lombok.Setter;

public class ColorSelectionEntry implements View.OnClickListener {
	private final TextView titleView;
	private final ColorCircleView colorView;
	private @Setter OnClickListener onClickListener;

	interface OnClickListener {
		void onEntryClick();
	}

	public ColorSelectionEntry(Context context, LinearLayout view) {
		LayoutInflater.from(context).inflate(R.layout.entry_color, view, true);
		view.setOnClickListener(this);
		titleView = (TextView) view.findViewById(R.id.title);
		colorView = (ColorCircleView) view.findViewById(R.id.color);
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
