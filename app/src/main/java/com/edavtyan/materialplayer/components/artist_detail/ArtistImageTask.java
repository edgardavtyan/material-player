package com.edavtyan.materialplayer.components.artist_detail;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class ArtistImageTask extends AsyncTask<String, Void, Bitmap> {

	private final ArtistDetailImageLoader artistDetailImageLoader;

	public interface OnArtLoadedCallback {
		void OnArtLoaded(Bitmap art);
	}

	private final OnArtLoadedCallback onArtLoadedCallback;

	public ArtistImageTask(
			ArtistDetailImageLoader artistDetailImageLoader,
			OnArtLoadedCallback onArtLoadedCallback) {
		this.artistDetailImageLoader = artistDetailImageLoader;
		this.onArtLoadedCallback = onArtLoadedCallback;
	}

	@Override
	protected Bitmap doInBackground(String... title) {
		return artistDetailImageLoader.load(title[0]);
	}

	@Override
	protected void onPostExecute(Bitmap art) {
		onArtLoadedCallback.OnArtLoaded(art);
	}
}
