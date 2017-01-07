package com.edavtyan.materialplayer.lib.lastfm;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Setter @Getter
public class LastfmArtistInfo {
	private static final String KEY_LARGE = "large";
	private static final String KEY_XLARGE = "xlarge";
	private static final String KEY_MEGA = "mega";

	private String largeImageUrl;
	private String extraLargeImageUrl;
	private String megaImageUrl;

	public String toJson() {
		try {
			JSONObject infoJson = new JSONObject();
			infoJson.put(KEY_LARGE, largeImageUrl);
			infoJson.put(KEY_XLARGE, extraLargeImageUrl);
			infoJson.put(KEY_MEGA, megaImageUrl);
			return infoJson.toString();
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static LastfmArtistInfo fromJson(String json) {
		try {
			JSONObject infoJson = new JSONObject(json);
			LastfmArtistInfo info = new LastfmArtistInfo();
			info.setLargeImageUrl(infoJson.getString(KEY_LARGE));
			info.setExtraLargeImageUrl(infoJson.getString(KEY_XLARGE));
			info.setMegaImageUrl(infoJson.getString(KEY_MEGA));
			return info;
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
