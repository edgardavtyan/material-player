package com.edavtyan.materialplayer.lib.file_storage;

import android.content.Context;
import android.graphics.Bitmap;

import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;

public class BitmapFileStorage extends BaseFileStorage {
	private final TestableBitmapFactory bitmapFactory;

	public BitmapFileStorage(Context context, String dirName) {
		super(context, dirName);
		bitmapFactory = new TestableBitmapFactory();
	}

	public Bitmap load(String filename) {
		byte[] bytes = loadBytes(filename);
		return bitmapFactory.fromByteArray(bytes);
	}
}
