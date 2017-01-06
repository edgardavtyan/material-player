package com.edavtyan.materialplayer.lib.lastfm;

import java.util.Locale;

public final class LastfmArtistInfoUrl {
	private static final String apiUrlStr =
			"http://ws.audioscrobbler.com/2.0/" +
			"?method=artist.getinfo" +
			"&artist=%s" +
			"&api_key=%s" +
			"&format=json";

	private LastfmArtistInfoUrl() {
	}

	public static String build(String apiKey, String artistTitle) {
		return String.format(Locale.getDefault(), apiUrlStr, artistTitle, apiKey);
	}
}
