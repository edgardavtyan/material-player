package com.edavtyan.materialplayer.lib.testable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class TestableImageButton extends ImageButton {
	public TestableImageButton(Context context) {
		super(context);
	}

	public TestableImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TestableImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setColorFilterNonFinal(int color) {
		super.setColorFilter(color);
	}
}
