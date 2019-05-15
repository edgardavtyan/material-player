package com.edavtyan.materialplayer.lib.music_api;

import com.edavtyan.materialplayer.utils.WebClient;

import org.json.JSONException;
import org.json.JSONObject;

public class AudioDBApi extends MusicApi {
	private final WebClient webClient;
	private final String apiKey;

	public AudioDBApi(WebClient webClient, MusicApiFileStorage fileStorage, String apiKey) {
		super(fileStorage);
		this.webClient = webClient;
		this.apiKey = apiKey;
	}

	@Override
	protected MusicApiInfo getInfoFromApi(String artistTitle) throws InfoNotFoundException {
		try {
			String url = buildUri(artistTitle);
			String jsonString = webClient.getString(url);
			MusicApiInfo info = new MusicApiInfo();
			JSONObject root = new JSONObject(jsonString);

			if (root.getString("artists").equals("null")) {
				throw new InfoNotFoundException(artistTitle);
			}

			JSONObject firstResult = root.getJSONArray("artists").getJSONObject(0);
			info.setImageUrl(firstResult.getString("strArtistThumb"));
			return info;
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	private String buildUri(String artist) {
		String urlBase = "https://www.theaudiodb.com/api/v1/json/%s/search.php?s=%s";
		return String.format(urlBase, apiKey, artist);
	}
}
