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
		int albumId = track.getAlbumId();

		if (memoryCache.contains(albumId)) {
			return memoryCache.get(albumId);
		}

		if (dataStorage.exists(albumId)) {
			Bitmap image = dataStorage.load(albumId);
			memoryCache.put(albumId, image);
			return image;
		}

		byte[] imageBytes = artReader.getAlbumArtBytes(track.getPath());
		Bitmap image = bitmapFactory.fromByteArray(imageBytes);
		dataStorage.saveBytes(albumId, imageBytes);
		memoryCache.put(albumId, image);
		return image;
	}
}
