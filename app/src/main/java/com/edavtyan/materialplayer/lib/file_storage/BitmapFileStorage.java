package com.edavtyan.materialplayer.lib.file_storage;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;

import java.io.ByteArrayOutputStream;

public class BitmapFileStorage extends BaseFileStorage {
	private final TestableBitmapFactory bitmapFactory;

	public BitmapFileStorage(String dirName, TestableBitmapFactory bitmapFactory) {
		super(dirName);
		this.bitmapFactory = bitmapFactory;
	}

	public void save(String filename, Bitmap bitmap) {
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] bitmapBytes = stream.toByteArray();
			stream.close();
			saveBytes(filename, bitmapBytes);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public Bitmap load(String filename) {
		return bitmapFactory.fromByteArray(loadBytes(filename));
	}
}
