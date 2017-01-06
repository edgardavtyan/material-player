package com.edavtyan.materialplayer.components.artist_detail;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.DataStorage;
import com.edavtyan.materialplayer.utils.WebClient;

import java.io.File;

public class ArtistDetailImageLoader {
	private static final LruCache<String, Bitmap> artistImageCache;

	static {
		artistImageCache = new LruCache<>(1000 * 1000 * 4); // 4MB
	}

	private final WebClient webClient;
	private final LastfmApi lastfmApi;
	private final TestableBitmapFactory bitmapFactory;
	private final DataStorage dataStorage;

	public ArtistDetailImageLoader(
			WebClient webClient,
			LastfmApi lastfmApi,
			TestableBitmapFactory bitmapFactory,
			DataStorage dataStorage) {
		this.webClient = webClient;
		this.lastfmApi = lastfmApi;
		this.bitmapFactory = bitmapFactory;
		this.dataStorage = dataStorage;
	}

	public Bitmap load(String artistTitle) {
		Bitmap artFromLruCache = artistImageCache.get(artistTitle);
		if (artFromLruCache != null) {
			return artFromLruCache;
		}

		File artFile = dataStorage.getArtistFile(artistTitle);
		if (artFile.exists()) {
			Bitmap artFromFileSystem = bitmapFactory.fromPath(artFile.getAbsolutePath());
			artistImageCache.put(artistTitle, artFromFileSystem);
			return artFromFileSystem;
		}

		try {
			String imageUrl = lastfmApi.getArtistInfo(artistTitle).getMegaImageUrl();
			byte[] imageBytes = webClient.get(imageUrl).bytes();
			dataStorage.saveFile(artFile, imageBytes);
			Bitmap imageFromApi = bitmapFactory.fromByteArray(imageBytes);
			artistImageCache.put(artistTitle, imageFromApi);
			return imageFromApi;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public Bitmap getArtFromLocalStorage(String artistTitle) {
		Bitmap artFromLruCache = artistImageCache.get(artistTitle);
		if (artFromLruCache != null) {
			return artFromLruCache;
		}

		File artFile = dataStorage.getArtistFile(artistTitle);
		if (artFile.exists()) {
			Bitmap artFromFileSystem = bitmapFactory.fromPath(artFile.getAbsolutePath());
			artistImageCache.put(artistTitle, artFromFileSystem);
			return artFromFileSystem;
		}

		return null;
	}
}
