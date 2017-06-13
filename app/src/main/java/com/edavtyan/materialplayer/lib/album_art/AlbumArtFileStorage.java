package com.edavtyan.materialplayer.lib.album_art;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.lib.file_storage.BitmapFileStorage;

public class AlbumArtFileStorage extends BitmapFileStorage {
	public AlbumArtFileStorage() {
		super("album_art");
	}

	public boolean exists(int albumId) {
		return exists(Integer.toString(albumId));
	}

	public Bitmap load(int albumId) {
		return load(Integer.toString(albumId));
	}

	public void saveBytes(int albumId, byte[] data) {
		saveBytes(Integer.toString(albumId), data);
	}
}
