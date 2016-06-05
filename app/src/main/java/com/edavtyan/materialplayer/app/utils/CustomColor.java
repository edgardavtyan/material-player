package com.edavtyan.materialplayer.app.utils;

import android.graphics.Color;

public class CustomColor {
	private final int red;
	private final int green;
	private final int blue;

	public CustomColor(int color) {
		this.red = Color.red(color);
		this.green = Color.green(color);
		this.blue = Color.blue(color);
	}

	public int fade(int alpha) {
		if (alpha > 255) alpha = 255;
		return Color.argb(alpha, red, green, blue);
	}

}
