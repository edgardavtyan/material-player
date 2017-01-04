package com.edavtyan.materialplayer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.edavtyan.materialplayer.R;

import org.json.JSONObject;

import java.io.File;
import java.util.Locale;

public class ArtistImageProvider {
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
	private final DataStorage dataStorage;

	public ArtistImageProvider(Context context) {
		this.context = context;
		this.webClient = new WebClient();
		this.dataStorage = new DataStorage();
	}

	public Bitmap load(String artistTitle) {
		Bitmap artFromLruCache = artistArtCache.get(artistTitle);
		if (artFromLruCache != null) {
			return artFromLruCache;
		}

		File artistArtFile = dataStorage.getArtistFile(artistTitle);
		if (artistArtFile.exists()) {
			Bitmap artFromFileSystem = BitmapFactory.decodeFile(artistArtFile.getAbsolutePath());
			artistArtCache.put(artistTitle, artFromFileSystem);
			return artFromFileSystem;
		}

		try {
			String jsonResponse = webClient.getString(getFullApiUrl(artistTitle)).string();
			JSONObject json = new JSONObject(jsonResponse);
			String artistArtUrl = json.getJSONObject("artist")
									  .getJSONArray("image")
									  .getJSONObject(4)
									  .getString("#text");
			byte[] artistArt = webClient.getString(artistArtUrl).bytes();
			dataStorage.saveFile(artistArtFile, artistArt);
			Bitmap artistArtFromAPI = BitmapFactory.decodeByteArray(artistArt, 0, artistArt.length);
			artistArtCache.put(artistTitle, artistArtFromAPI);
			return artistArtFromAPI;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getFullApiUrl(String artistTitle) {
		String apiKey = context.getString(R.string.lastfm_api_key);
		return String.format(Locale.getDefault(), apiUrlStr, artistTitle, apiKey);
	}
}
