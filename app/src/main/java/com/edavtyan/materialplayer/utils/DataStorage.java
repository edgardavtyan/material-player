package com.edavtyan.materialplayer.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class DataStorage {
	public static final File DIR_BASE = Environment.getExternalStorageDirectory();
	public static final File DIR_DATA = new File(DIR_BASE, "MaterialPlayer");
	public static final File DIR_ART = new File(DIR_DATA, "artwork");
	public static final File DIR_ARTIST_ART = new File(DIR_DATA, "artist_art");
	public static final File DIR_LOG = new File(DIR_DATA, "logs");

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public DataStorage() {
		DIR_ART.mkdirs();
		DIR_LOG.mkdirs();
		DIR_ARTIST_ART.mkdirs();
	}

	public File getArtFile(int albumId) {
		return new File(DIR_ART, Integer.toString(albumId));
	}

	public File getArtistFile(String artistTitle) {
		return new File(DIR_ARTIST_ART, artistTitle);
	}

	public void saveFile(File file, byte[] data) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(data);
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
