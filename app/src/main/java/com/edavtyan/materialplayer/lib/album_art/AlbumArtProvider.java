package com.edavtyan.materialplayer.lib.album_art;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;

public class AlbumArtProvider {
	private final AlbumArtFileStorage dataStorage;
	private final AlbumArtMemoryCache memoryCache;
	private final AlbumArtReader artReader;
	private final TestableBitmapFactory bitmapFactory;

	public AlbumArtProvider(
			AlbumArtFileStorage dataStorage,
			AlbumArtMemoryCache memoryCache,
			AlbumArtReader artReader,
			TestableBitmapFactory bitmapFactory) {
		this.dataStorage = dataStorage;
		this.memoryCache = memoryCache;
		this.artReader = artReader;
		this.bitmapFactory = bitmapFactory;
	}

	public Bitmap load(Track track) {
		if (memoryCache.contains(track.getAlbumId())) {
			return memoryCache.get(track.getAlbumId());
		}

		String imagePath = Integer.toString(track.getAlbumId());
		if (dataStorage.exists(imagePath)) {
			Bitmap image = dataStorage.load(imagePath);

			if (image != null) {
				memoryCache.put(track.getAlbumId(), image);
			}

			return image;
		}

		try {
			byte[] imageBytes = artReader.getAlbumArtBytes(track.getPath());
			dataStorage.saveBytes(imagePath, imageBytes);
			Bitmap image = bitmapFactory.fromByteArray(imageBytes);
			memoryCache.put(track.getAlbumId(), image);
			return image;
		} catch (Exception e) {
			return null;
		}
	}
}
