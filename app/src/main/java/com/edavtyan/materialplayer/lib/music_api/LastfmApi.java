package com.edavtyan.materialplayer.lib.music_api;

import com.edavtyan.materialplayer.utils.WebClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LastfmApi implements MusicApi {
	private final WebClient webClient;
	private final String apiKey;
	private final MusicApiFileStorage fileStorage;

	public LastfmApi(WebClient webClient, MusicApiFileStorage fileStorage, String apiKey) {
		this.webClient = webClient;
		this.apiKey = apiKey;
		this.fileStorage = fileStorage;
	}

	@Override
	public MusicApiInfo getArtistInfo(String artistTitle) {
		if (fileStorage.exists(artistTitle)) {
			return MusicApiInfo.fromJson(fileStorage.load(artistTitle));
		}

		MusicApiInfo artistInfo = getInfoFromApi(artistTitle);
		fileStorage.save(artistTitle, artistInfo.toJson());
		return artistInfo;
	}

	private MusicApiInfo getInfoFromApi(String artistTitle) {
		try {
			String url = LastfmArtistInfoUrl.build(apiKey, artistTitle);
			String jsonString = webClient.getString(url);
			MusicApiInfo artistInfo = new MusicApiInfo();
			JSONObject json = new JSONObject(jsonString);
			JSONArray artistImages = json.getJSONObject("artist").getJSONArray("image");
			artistInfo.setImageUrl(artistImages.getJSONObject(2).getString("#text"));
			return artistInfo;
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}
