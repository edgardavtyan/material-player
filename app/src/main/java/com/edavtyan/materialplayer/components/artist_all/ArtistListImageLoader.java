package com.edavtyan.materialplayer.components.artist_all;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.utils.WebClient;

public class ArtistListImageLoader {
	private final LastfmApi lastfmApi;
	private final ArtistListImageFileStorage fileStorage;
	private final ArtistListImageMemoryCache memoryCache;
	private final WebClient webClient;

	public ArtistListImageLoader(
			LastfmApi lastfmApi,
			ArtistListImageFileStorage fileStorage,
			ArtistListImageMemoryCache memoryCache) {
		this.lastfmApi = lastfmApi;
		this.fileStorage = fileStorage;
		this.memoryCache = memoryCache;
		this.webClient = new WebClient();
	}

	public Bitmap getImageFromCache(String artistTitle) {
		if (memoryCache.exists(artistTitle)) {
			return memoryCache.get(artistTitle);
		}

		return null;
	}

	public Bitmap load(String artistTitle) {
		try {
			if (fileStorage.exists(artistTitle)) {
				Bitmap image = fileStorage.loadBitmap(artistTitle);
				if (memoryCache.exists(artistTitle)) {
					memoryCache.put(artistTitle, image);
				}
				return image;
			}

			String url = lastfmApi.getArtistInfo(artistTitle).getLargeImageUrl();
			byte[] artBytes = webClient.get(url).bytes();
			Bitmap art = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);

			memoryCache.put(artistTitle, art);
			fileStorage.save(artistTitle, art);

			return art;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
