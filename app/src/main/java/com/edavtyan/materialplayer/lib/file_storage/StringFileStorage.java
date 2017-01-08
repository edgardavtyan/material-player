package com.edavtyan.materialplayer.lib.file_storage;

public class StringFileStorage extends BaseFileStorage {
	public StringFileStorage(String dirName) {
		super(dirName);
	}

	public void save(String filename, String data) {
		saveBytes(filename, data.getBytes());
	}

	public String load(String filename) {
		return new String(loadBytes(filename));
	}
}
