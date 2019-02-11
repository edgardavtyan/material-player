package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.content.Context;

import com.edavtyan.materialplayer.lib.file_storage.BitmapFileStorage;

public class ArtistListImageFileStorage extends BitmapFileStorage {
	public ArtistListImageFileStorage(Context context) {
		super(context, "artist_images");
	}
}
