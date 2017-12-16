package com.edavtyan.materialplayer.lib.file_storage;

import android.content.Context;

import com.edavtyan.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public abstract class BaseFileStorage {
	private final File dir;

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public BaseFileStorage(Context context, String dirName) {
		dir = new File(context.getCacheDir(), dirName);
		dir.mkdirs();
	}

	public void saveBytes(String filename, byte[] data) {
		File file = new File(dir, filename);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(data, 0, data.length);
			fileOutputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeStream(fileOutputStream);
		}
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public byte[] loadBytes(String filename) {
		File file = new File(dir, filename);
		byte[] data = new byte[(int) file.length()];
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(data, 0, data.length);
			return data;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeStream(fileInputStream);
		}
	}

	public boolean exists(String filename) {
		return new File(dir, filename).exists();
	}
}
