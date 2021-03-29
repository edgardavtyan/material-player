package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

public class ArtistListImageTask extends AsyncTask<String, Void, String> {
	private final ArtistListImageLoader imageLoader;
	private final Callback callback;

	interface Callback {
		void onArtLoaded(@Nullable String artLink);
	}

	public ArtistListImageTask(ArtistListImageLoader imageLoader, Callback callback) {
		this.imageLoader = imageLoader;
		this.callback = callback;
	}

	@Override
	@Nullable
	protected String doInBackground(String... title) {
		try {
			return imageLoader.getImageLink(title[0]);
		} catch (CouldNotLoadImageException e) {
			return null;
		}
	}

	@Override
	protected void onPostExecute(@Nullable String artLink) {
		callback.onArtLoaded(artLink);
	}
}
