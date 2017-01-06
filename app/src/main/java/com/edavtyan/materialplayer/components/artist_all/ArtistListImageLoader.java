package com.edavtyan.materialplayer.components.artist_all;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.utils.WebClient;

public class ArtistListImageLoader {
	private static final LruCache<String, Bitmap> lruCache;

	static {
		lruCache = new LruCache<>(1000 * 1000 * 4); // 4MB
	}

	private final LastfmApi lastfmApi;
	private final ArtistListImageFileStorage fileStorage;
	private final WebClient webClient;

	public ArtistListImageLoader(LastfmApi lastfmApi, ArtistListImageFileStorage fileStorage) {
		this.lastfmApi = lastfmApi;
		this.fileStorage = fileStorage;
		this.webClient = new WebClient();
	}

	public Bitmap load(String artistTitle) {
		try {
			if (lruCache.get(artistTitle) != null) {
				return lruCache.get(artistTitle);
			}

			if (fileStorage.exists(artistTitle)) {
				return fileStorage.loadBitmap(artistTitle);
			}

			String url = lastfmApi.getArtistInfo(artistTitle).getLargeImageUrl();
			byte[] artBytes = webClient.get(url).bytes();
			Bitmap art = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);

			lruCache.put(artistTitle, art);
			fileStorage.save(artistTitle, art);

			return art;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
