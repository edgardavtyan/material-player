package com.edavtyan.materialplayer.lib.testable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TestableBitmapFactory {
	public Bitmap fromPath(String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inSampleSize = 2;
		options.inDither = true;
		return BitmapFactory.decodeFile(path, options);
	}
}
