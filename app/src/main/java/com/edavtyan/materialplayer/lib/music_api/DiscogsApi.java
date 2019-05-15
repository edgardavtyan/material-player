package com.edavtyan.materialplayer.lib.music_api;

import com.edavtyan.materialplayer.utils.WebClient;

import org.json.JSONObject;

public class DiscogsApi extends MusicApi {
	private final WebClient webClient;
	private final String key;
	private final String secret;

	public DiscogsApi(WebClient webClient, MusicApiFileStorage fileStorage, String key, String secret) {
		super(fileStorage);
		this.webClient = webClient;
		this.key = key;
		this.secret = secret;
	}

	@Override
	protected MusicApiInfo getInfoFromApi(String artist) {
		try {
			String url = buildUrl(artist);
			String jsonString = webClient.getString(url);
			MusicApiInfo info = new MusicApiInfo();
			JSONObject root = new JSONObject(jsonString);
			JSONObject firstResult = root.getJSONArray("results").getJSONObject(0);
			info.setImageUrl(firstResult.getString("cover_image"));
			return info;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String buildUrl(String artist) {
		String url = "https://api.discogs.com/database/search?q=%s&type=artist&key=%s&secret=%s";
		return String.format(url, artist, key, secret);
	}
}
