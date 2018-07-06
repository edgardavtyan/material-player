package com.edavtyan.materialplayer.lib.lyrics;

import org.jsoup.Jsoup;

import java.io.IOException;

public class LyricsApi {
	private static final String URL_TEMPLATE = "http://lyrics.wikia.com/wiki/%s:%s";

	public String getLyrics(String artist, String track) {
		try {
			String url = String.format(URL_TEMPLATE, artist, track);
			String lyrics = Jsoup.connect(url).get().select(".lyricbox").html().replace("<br>", "");
			return lyrics;
		} catch (IOException e) {
			return null;
		}
	}
}
