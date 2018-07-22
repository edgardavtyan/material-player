package com.edavtyan.materialplayer.lib.lyrics;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LyricsApi {
	private static final String URL_TEMPLATE = "http://lyrics.wikia.com/wiki/%s:%s";

	public String getLyrics(String artist, String track)
	throws LyricsNotFoundException, LyricsConnectionException {
		try {
			String url = String.format(URL_TEMPLATE, encode(artist), encode(track));
			Connection.Response response = Jsoup.connect(url)
												.ignoreHttpErrors(true)
												.method(Connection.Method.GET)
												.execute();
			String lyrics = response.parse()
									.select(".lyricbox")
									.html()
									.replace("<br>", "")
									.replace("\n<div class=\"lyricsbreak\"></div>", "");

			if (response.statusCode() == 404 && lyrics.isEmpty()) {
				throw new LyricsNotFoundException(artist, track);
			}

			return lyrics;
		} catch (IOException e) {
			throw new LyricsConnectionException(e);
		}
	}

	private String encode(String str) {
		try {
			String strSpaces = str.replace(" ", "_");
			String strEncoded = URLEncoder.encode(strSpaces, "UTF-8");
			return strEncoded;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
