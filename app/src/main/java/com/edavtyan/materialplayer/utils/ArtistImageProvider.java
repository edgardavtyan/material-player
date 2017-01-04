package com.edavtyan.materialplayer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.edavtyan.materialplayer.R;

import org.json.JSONObject;

import java.util.Locale;

public class ArtistImageProvider {
	@SuppressWarnings("SpellCheckingInspection")
	private static final String apiUrlStr =
			"http://ws.audioscrobbler.com/2.0/" +
			"?method=artist.getinfo" +
			"&artist=%s" +
			"&api_key=%s" +
			"&format=json";

	private final Context context;
	private final WebClient webClient;

	public ArtistImageProvider(Context context) {
		this.context = context;
		this.webClient = new WebClient();
	}

	public Bitmap load(String artistTitle) {
		try {
			String jsonResponse = webClient.getString(getFullApiUrl(artistTitle)).string();
			JSONObject json = new JSONObject(jsonResponse);
			String artistArtUrl = json.getJSONObject("artist")
									  .getJSONArray("image")
									  .getJSONObject(4)
									  .getString("#text");
			byte[] artistArt = webClient.getString(artistArtUrl).bytes();
			return BitmapFactory.decodeByteArray(artistArt, 0, artistArt.length);
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
