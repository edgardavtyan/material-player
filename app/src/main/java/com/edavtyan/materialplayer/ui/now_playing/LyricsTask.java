package com.edavtyan.materialplayer.ui.now_playing;

import android.os.AsyncTask;

import com.edavtyan.materialplayer.lib.lyrics.LyricsApi;

public class LyricsTask extends AsyncTask<String, Void, String> {
	private final LyricsApi api;
	private final OnLyricsLoadedCallback callback;

	public interface OnLyricsLoadedCallback {
		void onLyricsLoaded(String lyrics);
	}

	public LyricsTask(LyricsApi api, OnLyricsLoadedCallback callback) {
		this.api = api;
		this.callback = callback;
	}

	@Override
	protected String doInBackground(String... data) {
		String artist = data[0];
		String track = data[1];
		return api.getLyrics(artist, track);
	}

	@Override
	protected void onPostExecute(String lyrics) {
		callback.onLyricsLoaded(lyrics);
	}
}
