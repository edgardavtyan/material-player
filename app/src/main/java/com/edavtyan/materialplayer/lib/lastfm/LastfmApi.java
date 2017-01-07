package com.edavtyan.materialplayer.lib.lastfm;

import com.edavtyan.materialplayer.utils.WebClient;

import org.json.JSONArray;
import org.json.JSONObject;

public class LastfmApi {
	private final WebClient webClient;
	private final String apiKey;
	private final LastfmArtistInfoFileStorage fileStorage;

	public LastfmApi(WebClient webClient, LastfmArtistInfoFileStorage fileStorage,  String apiKey) {
		this.webClient = webClient;
		this.apiKey = apiKey;
		this.fileStorage = fileStorage;
	}

	public LastfmArtistInfo getArtistInfo(String artistTitle) {
		if (fileStorage.exists(artistTitle)) {
			return LastfmArtistInfo.fromJson(fileStorage.load(artistTitle));
		}

		LastfmArtistInfo artistInfo = getInfoFromApi(artistTitle);
		fileStorage.save(artistTitle, artistInfo.toJson());
		return artistInfo;
	}

	private LastfmArtistInfo getInfoFromApi(String artistTitle) {
		try {
			String url = LastfmArtistInfoUrl.build(apiKey, artistTitle);
			String jsonString = webClient.get(url).string();
			LastfmArtistInfo artistInfo = new LastfmArtistInfo();
			JSONObject json = new JSONObject(jsonString);
			JSONArray artistImages = json.getJSONObject("artist").getJSONArray("image");
			artistInfo.setLargeImageUrl(artistImages.getJSONObject(2).getString("#text"));
			artistInfo.setExtraLargeImageUrl(artistImages.getJSONObject(3).getString("#text"));
			artistInfo.setMegaImageUrl(artistImages.getJSONObject(4).getString("#text"));
			return artistInfo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
