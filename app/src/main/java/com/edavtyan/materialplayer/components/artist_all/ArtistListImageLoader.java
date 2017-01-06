package com.edavtyan.materialplayer.components.artist_all;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.utils.DataStorage;
import com.edavtyan.materialplayer.utils.WebClient;

import java.io.File;

public class ArtistListImageLoader {
	private static final File artDir;
	private static final LruCache<String, Bitmap> lruCache;

	static {
		artDir = new File(DataStorage.DIR_DATA, "artist_art");
		lruCache = new LruCache<>(1000 * 1000 * 4); // 4MB
	}

	private final LastfmApi lastfmApi;
	private final WebClient webClient;

	public ArtistListImageLoader(Context context, LastfmApi lastfmApi) {
		this.lastfmApi = lastfmApi;
		this.webClient = new WebClient();
	}

	public Bitmap load(String artistTitle) {
		try {
			if (lruCache.get(artistTitle) != null) {
				return lruCache.get(artistTitle);
			}

			String url = lastfmApi.getArtistInfo(artistTitle).getLargeImageUrl();
			byte[] artBytes = webClient.get(url).bytes();
			Bitmap art = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);

			lruCache.put(artistTitle, art);

			return art;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
