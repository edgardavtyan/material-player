package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.DataStorage;
import com.edavtyan.materialplayer.utils.WebClient;

import org.json.JSONObject;

import java.io.File;
import java.util.Locale;

public class ArtistDetailImageLoader {
	@SuppressWarnings("SpellCheckingInspection")
	private static final String apiUrlStr =
			"http://ws.audioscrobbler.com/2.0/" +
			"?method=artist.getinfo" +
			"&artist=%s" +
			"&api_key=%s" +
			"&format=json";

	private static final LruCache<String, Bitmap> artistArtCache;

	static {
		artistArtCache = new LruCache<>(1000 * 1000 * 4); // 4MB
	}

	private final Context context;
	private final WebClient webClient;
	private final TestableBitmapFactory bitmapFactory;
	private final DataStorage dataStorage;

	public ArtistDetailImageLoader(
			Context context,
			WebClient webClient,
			TestableBitmapFactory bitmapFactory,
			DataStorage dataStorage) {
		this.context = context;
		this.webClient = webClient;
		this.bitmapFactory = bitmapFactory;
		this.dataStorage = dataStorage;
	}

	public boolean isArtSavedLocally(String artistTitle) {
		Bitmap artFromLruCache = artistArtCache.get(artistTitle);
		File artFile = dataStorage.getArtistFile(artistTitle);
		return (artFromLruCache == null || artFile.exists());
	}

	public Bitmap load(String artistTitle) {
		Bitmap artFromLruCache = artistArtCache.get(artistTitle);
		if (artFromLruCache != null) {
			return artFromLruCache;
		}

		File artFile = dataStorage.getArtistFile(artistTitle);
		if (artFile.exists()) {
			Bitmap artFromFileSystem = bitmapFactory.fromPath(artFile.getAbsolutePath());
			artistArtCache.put(artistTitle, artFromFileSystem);
			return artFromFileSystem;
		}

		byte[] artBytesFromApi = getArtFromApi(artistTitle);
		dataStorage.saveFile(artFile, artBytesFromApi);
		Bitmap artFromApi = bitmapFactory.fromByteArray(artBytesFromApi);
		artistArtCache.put(artistTitle, artFromApi);
		return artFromApi;
	}

	public Bitmap getArtFromLocalStorage(String artistTitle) {
		Bitmap artFromLruCache = artistArtCache.get(artistTitle);
		if (artFromLruCache != null) {
			return artFromLruCache;
		}

		File artFile = dataStorage.getArtistFile(artistTitle);
		if (artFile.exists()) {
			Bitmap artFromFileSystem = bitmapFactory.fromPath(artFile.getAbsolutePath());
			artistArtCache.put(artistTitle, artFromFileSystem);
			return artFromFileSystem;
		}

		return null;
	}

	private String getFullApiUrl(String artistTitle) {
		String apiKey = context.getString(R.string.lastfm_api_key);
		return String.format(Locale.getDefault(), apiUrlStr, artistTitle, apiKey);
	}

	private byte[] getArtFromApi(String artistTitle) {
		try {
			String fullApiUrl = getFullApiUrl(artistTitle);
			String jsonResponse = webClient.get(fullApiUrl).string();
			JSONObject json = new JSONObject(jsonResponse);
			String artistArtUrl = json.getJSONObject("artist")
									  .getJSONArray("image")
									  .getJSONObject(4)
									  .getString("#text");
			return webClient.get(artistArtUrl).bytes();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
