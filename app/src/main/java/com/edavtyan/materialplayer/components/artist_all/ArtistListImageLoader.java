package com.edavtyan.materialplayer.components.artist_all;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.WebClient;

public class ArtistListImageLoader {
	private final TestableBitmapFactory bitmapFactory;
	private final LastfmApi lastfmApi;
	private final ArtistListImageFileStorage fileStorage;
	private final ArtistListImageMemoryCache memoryCache;
	private final WebClient webClient;

	public ArtistListImageLoader(
			WebClient webClient,
			TestableBitmapFactory bitmapFactory,
			LastfmApi lastfmApi,
			ArtistListImageFileStorage fileStorage,
			ArtistListImageMemoryCache memoryCache) {
		this.bitmapFactory = bitmapFactory;
		this.lastfmApi = lastfmApi;
		this.fileStorage = fileStorage;
		this.memoryCache = memoryCache;
		this.webClient = webClient;
	}

	public Bitmap getImageFromMemoryCache(String artistTitle) {
		if (memoryCache.exists(artistTitle)) {
			return memoryCache.get(artistTitle);
		}

		return null;
	}

	public Bitmap getImageFromFileSystemOrApi(String artistTitle) {
		try {
			if (fileStorage.exists(artistTitle)) {
				Bitmap image = fileStorage.loadBitmap(artistTitle);
				if (!memoryCache.exists(artistTitle)) {
					memoryCache.put(artistTitle, image);
				}
				return image;
			}

			String url = lastfmApi.getArtistInfo(artistTitle).getLargeImageUrl();
			byte[] artBytes = webClient.getBytes(url);
			Bitmap art = bitmapFactory.fromByteArray(artBytes);

			memoryCache.put(artistTitle, art);
			fileStorage.save(artistTitle, art);

			return art;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
