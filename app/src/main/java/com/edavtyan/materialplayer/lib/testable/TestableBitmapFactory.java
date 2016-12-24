package com.edavtyan.materialplayer.lib.testable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TestableBitmapFactory {
	public Bitmap fromPath(String path) {
		return BitmapFactory.decodeFile(path);
	}
}
