package com.edavtyan.materialplayer.lib.lyrics;

import android.support.annotation.Nullable;

public class LyricsProvider {
	private final LyricsApi api;
	private final LyricsStorage storage;

	public LyricsProvider(LyricsApi api, LyricsStorage storage) {
		this.api = api;
		this.storage = storage;
	}

	@Nullable
	public String getLyrics(String artist, String track)
	throws LyricsNotFoundException, LyricsConnectionException {
		if (storage.exists(artist, track)) {
			return storage.get(artist, track);
		}

		String lyrics = api.getLyrics(artist, track);

		if (lyrics != null) {
			storage.put(artist, track, lyrics);
		}

		return lyrics;
	}
}
