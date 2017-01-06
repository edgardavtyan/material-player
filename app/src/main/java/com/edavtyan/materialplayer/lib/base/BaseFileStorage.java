package com.edavtyan.materialplayer.lib.base;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class BaseFileStorage {
	private static final File DIR_BASE = Environment.getExternalStorageDirectory();
	private static final File DIR_DATA = new File(DIR_BASE, "MaterialPlayer");

	private final File dir;

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public BaseFileStorage(String dirName) {
		dir = new File(DIR_DATA, dirName);
		dir.mkdirs();
	}

	public void save(String filename, String data) {
		try {
			byte[] dataBytes = data.getBytes();
			File file = new File(dir, filename);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(dataBytes, 0, dataBytes.length);
			fileOutputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public String load(String filename) {
		try {
			File file = new File(dir, filename);
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}

			return stringBuilder.toString();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public boolean exists(String filename) {
		return new File(dir, filename).exists();
	}
}
