package com.edavtyan.materialplayer.lib.file_storage;

import android.content.Context;

public class StringFileStorage extends BaseFileStorage {
	public StringFileStorage(Context context, String dirName) {
		super(context, dirName);
	}

	public void save(String filename, String data) {
		saveBytes(filename, data.getBytes());
	}

	public String load(String filename) {
		return new String(loadBytes(filename));
	}
}
