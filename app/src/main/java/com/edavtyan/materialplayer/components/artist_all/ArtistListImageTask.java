package com.edavtyan.materialplayer.components.artist_all;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class ArtistListImageTask extends AsyncTask<String, Void, Bitmap> {
	private final ArtistListImageLoader imageLoader;
	private final Callback callback;

	interface Callback {
		void onArtLoaded(Bitmap art);
	}

	public ArtistListImageTask(ArtistListImageLoader imageLoader, Callback callback) {
		this.imageLoader = imageLoader;
		this.callback = callback;
	}

	@Override
	protected Bitmap doInBackground(String... title) {
		return imageLoader.getImageFromFileSystemOrApi(title[0]);
	}

	@Override
	protected void onPostExecute(Bitmap art) {
		callback.onArtLoaded(art);
	}
}
