package com.edavtyan.materialplayer.lib.album_art;

import android.content.Context;

import com.edavtyan.materialplayer.lib.file_storage.BitmapFileStorage;

public class AlbumArtFileStorage extends BitmapFileStorage {
	public AlbumArtFileStorage(Context context) {
		super(context, "album_art");
	}
}
