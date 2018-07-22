package com.edavtyan.materialplayer.ui.now_playing;

import android.os.AsyncTask;

import com.edavtyan.materialplayer.lib.lyrics.LyricsProvider;

public class LyricsTask extends AsyncTask<String, Void, String> {
	private final LyricsProvider provider;
	private final OnLyricsLoadedCallback callback;

	public interface OnLyricsLoadedCallback {
		void onLyricsLoaded(String lyrics);
	}

	public LyricsTask(LyricsProvider provider, OnLyricsLoadedCallback callback) {
		this.provider = provider;
		this.callback = callback;
	}

	@Override
	protected String doInBackground(String... data) {
		String artist = data[0];
		String track = data[1];
		return provider.getLyrics(artist, track);
	}

	@Override
	protected void onPostExecute(String lyrics) {
		callback.onLyricsLoaded(lyrics);
	}
}
