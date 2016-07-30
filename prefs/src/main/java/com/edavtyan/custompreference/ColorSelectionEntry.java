package com.edavtyan.custompreference;

import android.view.View;
import android.widget.TextView;

import lombok.Setter;

public class ColorSelectionEntry implements View.OnClickListener {
	private final TextView titleView;
	private final ColorCircleView colorView;
	private @Setter OnClickListener onClickListener;

	interface OnClickListener {
		void onEntryClick();
	}

	public ColorSelectionEntry(View view) {
		view.setOnClickListener(this);
		titleView = (TextView) view.findViewById(R.id.title);
		colorView = (ColorCircleView) view.findViewById(R.id.color);
	}

	public void setTitle(CharSequence title) {
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
