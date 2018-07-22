package com.edavtyan.materialplayer.ui.now_playing;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.AsyncTaskResult;
import com.edavtyan.materialplayer.lib.lyrics.LyricsProvider;

public class LyricsTask extends AsyncTask<String, Void, AsyncTaskResult<String>> {
	private final LyricsProvider provider;
	private final OnLyricsLoadedCallback callback;

	public interface OnLyricsLoadedCallback {
		void onLyricsLoaded(@Nullable String  lyrics, @Nullable Exception e);
	}

	public LyricsTask(LyricsProvider provider, OnLyricsLoadedCallback callback) {
		this.provider = provider;
		this.callback = callback;
	}

	@Override
	protected AsyncTaskResult<String> doInBackground(String... data) {
		String artist = data[0];
		String track = data[1];
		String lyrics = null;
		Exception exception = null;

		try {
			lyrics = provider.getLyrics(artist, track);
		} catch (Exception e) {
			exception = e;
		}

		return new AsyncTaskResult<>(lyrics, exception);
	}

	@Override
	protected void onPostExecute(AsyncTaskResult<String> result) {
		callback.onLyricsLoaded(result.getResult(), result.getException());
	}
}
