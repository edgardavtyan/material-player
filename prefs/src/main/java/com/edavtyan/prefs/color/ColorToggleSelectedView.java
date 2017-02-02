package com.edavtyan.prefs.color;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.R2;
import com.edavtyan.utils.generic.GenericFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorToggleSelectedView extends GenericFrameLayout {
	@BindView(R2.id.color_view) ColorCircleView colorView;
	@BindView(R2.id.check_icon) ImageView checkIcon;

	public ColorToggleSelectedView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ColorToggleSelectedView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
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

	private void init(Context context) {
		inflate(context, R.layout.view_color_toggle, this);
		ButterKnife.bind(this);
	}
}
