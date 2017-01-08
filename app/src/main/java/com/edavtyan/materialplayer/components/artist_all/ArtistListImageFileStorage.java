package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.lib.file_storage.BitmapFileStorage;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;

public class ArtistListImageFileStorage extends BitmapFileStorage {
	public ArtistListImageFileStorage(TestableBitmapFactory bitmapFactory) {
		super("artist_images", bitmapFactory);
	}
}
