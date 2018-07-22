package com.edavtyan.materialplayer.lib.lyrics;

public class LyricsProvider {
	private final LyricsApi api;
	private final LyricsStorage storage;

	public LyricsProvider(LyricsApi api, LyricsStorage storage) {
		this.api = api;
		this.storage = storage;
	}

	public String getLyrics(String artist, String track) {
		if (storage.exists(artist, track)) {
			return storage.get(artist, track);
		} else {
			String lyrics = api.getLyrics(artist, track);
			storage.put(artist, track, lyrics);
			return lyrics;
		}
	}
}
