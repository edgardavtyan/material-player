package com.edavtyan.materialplayer.lib.album_art;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

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

	@Nullable
	public Bitmap load(Track track) {
		int trackId = track.getId();

		if (memoryCache.contains(trackId)) {
			return memoryCache.get(trackId);
		}

		if (dataStorage.exists(trackId)) {
			Bitmap image = dataStorage.load(trackId);
			memoryCache.put(trackId, image);
			return image;
		}

		byte[] imageBytes = artReader.getAlbumArtBytes(track.getPath());

		if (imageBytes == null) {
			return null;
		}

		Bitmap image = bitmapFactory.fromByteArray(imageBytes);
		dataStorage.saveBytes(trackId, imageBytes);
		memoryCache.put(trackId, image);
		return image;
	}
}
