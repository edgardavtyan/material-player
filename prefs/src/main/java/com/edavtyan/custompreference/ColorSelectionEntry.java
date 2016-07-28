package com.edavtyan.custompreference;

import android.view.View;
import android.widget.TextView;

public class ColorSelectionEntry {
	private final TextView titleView;
	private final ColorCircleView colorView;


	public ColorSelectionEntry(View view, ColorSelectionController controller) {
		titleView = (TextView) view.findViewById(R.id.title);
		titleView.setText(controller.getTitle());

		colorView = (ColorCircleView) view.findViewById(R.id.color);
		colorView.setColor(controller.getCurrentColor());
	}


	public void setTitle(CharSequence title) {
		titleView.setText(title);
	}

	public void setColor(int color) {
		colorView.setColor(color);
	}
}
