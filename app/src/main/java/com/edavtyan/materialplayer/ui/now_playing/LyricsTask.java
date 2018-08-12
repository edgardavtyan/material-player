package com.edavtyan.materialplayer.ui.now_playing;

import android.os.AsyncTask;

import com.edavtyan.materialplayer.lib.AsyncTaskResult;
import com.edavtyan.materialplayer.lib.lyrics.LyricsConnectionException;
import com.edavtyan.materialplayer.lib.lyrics.LyricsNotFoundException;
import com.edavtyan.materialplayer.lib.lyrics.LyricsProvider;

public class LyricsTask extends AsyncTask<String, Void, AsyncTaskResult<String>> {
	private final LyricsProvider provider;
	private final OnLyricsLoadedCallback callback;

	public interface OnLyricsLoadedCallback {
		void onLyricsLoaded(String  lyrics);
		void onLyricsNotFound();
		void onConnectionError();
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
		if (result.getException() instanceof LyricsNotFoundException) {
			callback.onLyricsNotFound();
			return;
		}

		if (result.getException() instanceof LyricsConnectionException) {
			callback.onConnectionError();
			return;
		}

		callback.onLyricsLoaded(result.getResult());
	}
}
