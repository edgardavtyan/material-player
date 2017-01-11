package com.edavtyan.materialplayer.lib.file_storage;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;

import java.io.ByteArrayOutputStream;

public class BitmapFileStorage extends BaseFileStorage {
	private final TestableBitmapFactory bitmapFactory;

	public BitmapFileStorage(String dirName) {
		super(dirName);
		bitmapFactory = new TestableBitmapFactory();
	}

	public void save(String filename, Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] bitmapBytes = stream.toByteArray();
		saveBytes(filename, bitmapBytes);
	}

	public Bitmap load(String filename) {
		byte[] bytes = loadBytes(filename);
		return bitmapFactory.fromByteArray(bytes);
	}
}
