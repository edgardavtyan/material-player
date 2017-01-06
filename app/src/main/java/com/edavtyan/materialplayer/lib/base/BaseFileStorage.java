package com.edavtyan.materialplayer.lib.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
		save(filename, data.getBytes());
	}

	public void save(String filename, Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] bitmapBytes = stream.toByteArray();
		save(filename, bitmapBytes);
	}

	public void save(String filename, byte[] data) {
		try {
			File file = new File(dir, filename);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(data, 0, data.length);
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

	public Bitmap loadBitmap(String filename) {
		File file = new File(dir, filename);
		return BitmapFactory.decodeFile(file.getAbsolutePath());
	}

	public boolean exists(String filename) {
		return new File(dir, filename).exists();
	}
}
