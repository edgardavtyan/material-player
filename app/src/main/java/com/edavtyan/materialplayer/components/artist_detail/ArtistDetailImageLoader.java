package com.edavtyan.materialplayer.components.artist_detail;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.WebClient;

public class ArtistDetailImageLoader {
	private static final LruCache<String, Bitmap> artistImageCache;

	static {
		artistImageCache = new LruCache<>(1000 * 1000 * 4); // 4MB
	}

	private final WebClient webClient;
	private final LastfmApi lastfmApi;
	private final TestableBitmapFactory bitmapFactory;
	private final ArtistDetailImageFileStorage fileStorage;

	public ArtistDetailImageLoader(
			WebClient webClient,
			LastfmApi lastfmApi,
			TestableBitmapFactory bitmapFactory,
			ArtistDetailImageFileStorage fileStorage) {
		this.webClient = webClient;
		this.lastfmApi = lastfmApi;
		this.bitmapFactory = bitmapFactory;
		this.fileStorage = fileStorage;
	}

	public Bitmap getImageFromApi(String artistTitle) {
		try {
			String imageUrl = lastfmApi.getArtistInfo(artistTitle).getMegaImageUrl();
			byte[] imageBytes = webClient.get(imageUrl).bytes();
			Bitmap imageFromApi = bitmapFactory.fromByteArray(imageBytes);
			fileStorage.save(artistTitle, imageBytes);
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

		if (fileStorage.exists(artistTitle)) {
			Bitmap artFromFileSystem = fileStorage.loadBitmap(artistTitle);
			artistImageCache.put(artistTitle, artFromFileSystem);
			return artFromFileSystem;
		}

		return null;
	}
}
