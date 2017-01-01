package com.edavtyan.prefs.color;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.edavtyan.prefs.R;

public class ColorToggleSelectedView extends FrameLayout {

	private final ColorCircleView colorView;
	private final ImageView checkIcon;

	public ColorToggleSelectedView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.view_color_toggle, this);
		colorView = (ColorCircleView) findViewById(R.id.colorView);
		checkIcon = (ImageView) findViewById(R.id.checkIcon);
	}

	public ColorToggleSelectedView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		inflate(context, R.layout.view_color_toggle, this);
		colorView = (ColorCircleView) findViewById(R.id.colorView);
		checkIcon = (ImageView) findViewById(R.id.checkIcon);
	}

	public void setChecked(boolean checked) {
		checkIcon.setVisibility(checked ? VISIBLE : INVISIBLE);
	}

	public int getColor() {
		return colorView.getColor();
	}

	public void setColor(int color) {
		colorView.setColor(color);
	}

	@Override
	public void setLayoutParams(ViewGroup.LayoutParams params) {
		super.setLayoutParams(params);

		int padding = params.width / 8;
		checkIcon.setPadding(padding, padding, padding, padding);
	}
}
