package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.content.Context;

import com.edavtyan.materialplayer.lib.file_storage.BitmapFileStorage;

public class ArtistDetailImageFileStorage extends BitmapFileStorage {
	public ArtistDetailImageFileStorage(Context context) {
		super(context, "artist_images_big");
	}
}
