package com.edavtyan.materialplayer.lib.music_api;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class MusicApiInfo {
	private static final String KEY_IMAGE = "image";

	private String imageUrl;

	public static MusicApiInfo fromJson(String json) {
		try {
			JSONObject infoJson = new JSONObject(json);
			MusicApiInfo info = new MusicApiInfo();
			info.setImageUrl(infoJson.getString(KEY_IMAGE));
			return info;
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public String toJson() {
		try {
			JSONObject infoJson = new JSONObject();
			infoJson.put(KEY_IMAGE, imageUrl);
			return infoJson.toString();
		} catch (JSONException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
