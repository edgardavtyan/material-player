package com.edavtyan.materialplayer.lib.lyrics;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LyricsApi {
	private static final String URL_TEMPLATE = "http://lyrics.wikia.com/wiki/%s:%s";

	public String getLyrics(String artist, String track) {
		try {
			String url = String.format(URL_TEMPLATE, encode(artist), encode(track));
			String lyrics = Jsoup.connect(url)
								 .get()
								 .select(".lyricbox")
								 .html()
								 .replace("<br>", "")
								 .replace("\n<div class=\"lyricsbreak\"></div>", "");
			return lyrics;
		} catch (IOException e) {
			return null;
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
