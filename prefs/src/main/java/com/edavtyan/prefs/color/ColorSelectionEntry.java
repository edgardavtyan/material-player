package com.edavtyan.prefs.color;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.prefs.R;
import com.edavtyan.utils.generic.GenericLinearLayout;

import lombok.Setter;

public class ColorSelectionEntry implements View.OnClickListener {
	private final TextView titleView;
	private final ColorCircleView colorView;
	private @Setter OnClickListener onClickListener;

	interface OnClickListener {
		void onEntryClick();
	}

	public ColorSelectionEntry(Context context, GenericLinearLayout view) {
		LayoutInflater.from(context).inflate(R.layout.entry_color, view, true);
		view.setOnClickListener(this);
		titleView = view.findView(R.id.title);
		colorView = view.findView(R.id.color);
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
