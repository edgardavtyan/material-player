package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

public class ArtistListImageTask extends AsyncTask<String, Void, Bitmap> {
	private final ArtistListImageLoader imageLoader;
	private final Callback callback;

	interface Callback {
		void onArtLoaded(@Nullable Bitmap art);
	}

	public ArtistListImageTask(ArtistListImageLoader imageLoader, Callback callback) {
		this.imageLoader = imageLoader;
		this.callback = callback;
	}

	@Override
	@Nullable
	protected Bitmap doInBackground(String... title) {
		return imageLoader.getImageFromFileSystemOrApi(title[0]);
	}

	@Override
	protected void onPostExecute(@Nullable Bitmap art) {
		callback.onArtLoaded(art);
	}
}
