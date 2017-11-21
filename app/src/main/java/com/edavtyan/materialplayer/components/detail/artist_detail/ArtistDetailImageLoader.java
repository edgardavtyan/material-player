package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.WebClient;

public class ArtistDetailImageLoader {
	private final WebClient webClient;
	private final LastfmApi lastfmApi;
	private final TestableBitmapFactory bitmapFactory;
	private final ArtistDetailImageFileStorage fileStorage;
	private final ArtistDetailImageMemoryCache memoryCache;

	public ArtistDetailImageLoader(
			WebClient webClient,
			LastfmApi lastfmApi,
			TestableBitmapFactory bitmapFactory,
			ArtistDetailImageFileStorage fileStorage,
			ArtistDetailImageMemoryCache memoryCache) {
		this.webClient = webClient;
		this.lastfmApi = lastfmApi;
		this.bitmapFactory = bitmapFactory;
		this.fileStorage = fileStorage;
		this.memoryCache = memoryCache;
	}

	public Bitmap getImageFromApi(String artistTitle) {
		try {
			String imageUrl = lastfmApi.getArtistInfo(artistTitle).getMegaImageUrl();
			byte[] imageBytes = webClient.getBytes(imageUrl);
			Bitmap imageFromApi = bitmapFactory.fromByteArray(imageBytes);
			fileStorage.saveBytes(artistTitle, imageBytes);
			memoryCache.put(artistTitle, imageFromApi);
			return imageFromApi;
		} catch (Exception e) {
			return null;
		}
	}

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
