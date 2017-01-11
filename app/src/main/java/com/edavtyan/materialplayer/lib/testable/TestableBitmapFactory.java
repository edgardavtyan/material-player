package com.edavtyan.materialplayer.lib.testable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TestableBitmapFactory {
	public Bitmap fromByteArray(byte[] array) {
		return BitmapFactory.decodeByteArray(array, 0, array.length, getOptions());
	}

	private BitmapFactory.Options getOptions() {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		return options;
	}
}
