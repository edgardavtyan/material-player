package com.edavtyan.materialplayer.components.artist_detail;

import com.edavtyan.materialplayer.lib.file_storage.BitmapFileStorage;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;

public class ArtistDetailImageFileStorage extends BitmapFileStorage {
	public ArtistDetailImageFileStorage(TestableBitmapFactory bitmapFactory) {
		super("artist_images_big", bitmapFactory);
	}
}
