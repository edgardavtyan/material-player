package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.music_api.MusicApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.WebClient;

public class ArtistDetailImageLoader {
	private final WebClient webClient;
	private final MusicApi musicApi;
	private final TestableBitmapFactory bitmapFactory;
	private final ArtistDetailImageFileStorage fileStorage;
	private final ArtistDetailImageMemoryCache memoryCache;

	public ArtistDetailImageLoader(
			WebClient webClient,
			MusicApi musicApi,
			TestableBitmapFactory bitmapFactory,
			ArtistDetailImageFileStorage fileStorage,
			ArtistDetailImageMemoryCache memoryCache) {
		this.webClient = webClient;
		this.musicApi = musicApi;
		this.bitmapFactory = bitmapFactory;
		this.fileStorage = fileStorage;
		this.memoryCache = memoryCache;
	}

	@Nullable
	public Bitmap getImageFromApi(String artistTitle) {
		try {
			String imageUrl = musicApi.getArtistInfo(artistTitle).getImageUrl();
			byte[] imageBytes = webClient.getBytes(imageUrl);
			Bitmap imageFromApi = bitmapFactory.fromByteArray(imageBytes);
			fileStorage.saveBytes(artistTitle, imageBytes);
			memoryCache.put(artistTitle, imageFromApi);
			return imageFromApi;
		} catch (Exception e) {
			return null;
		}
	}

	@Nullable
	public Bitmap getImageFromCache(String artistTitle) {
		if (memoryCache.exists(artistTitle)) {
			return memoryCache.get(artistTitle);
		}

		if (fileStorage.exists(artistTitle)) {
			Bitmap imageFromFileStorage = fileStorage.load(artistTitle);
			memoryCache.put(artistTitle, imageFromFileStorage);
			return imageFromFileStorage;
		}

		return null;
	}
}
