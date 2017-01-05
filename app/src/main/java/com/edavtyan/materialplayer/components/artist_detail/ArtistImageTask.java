package com.edavtyan.materialplayer.components.artist_detail;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.edavtyan.materialplayer.utils.ArtistArtProvider;

public class ArtistImageTask extends AsyncTask<String, Void, Bitmap> {

	private final ArtistArtProvider artistArtProvider;

	public interface OnArtLoadedCallback {
		void OnArtLoaded(Bitmap art);
	}

	private final OnArtLoadedCallback onArtLoadedCallback;

	public ArtistImageTask(
			ArtistArtProvider artistArtProvider,
			OnArtLoadedCallback onArtLoadedCallback) {
		this.artistArtProvider = artistArtProvider;
		this.onArtLoadedCallback = onArtLoadedCallback;
	}

	@Override
	protected Bitmap doInBackground(String... title) {
		return artistArtProvider.load(title[0]);
	}

	@Override
	protected void onPostExecute(Bitmap art) {
		onArtLoadedCallback.OnArtLoaded(art);
	}
}
