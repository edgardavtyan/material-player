package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class ArtistDetailImageTask extends AsyncTask<String, Void, Bitmap> {

	private final ArtistDetailImageLoader imageLoader;
	private final OnImageLoadedCallback onImageLoadedCallback;

	public interface OnImageLoadedCallback {
		void OnImageLoaded(Bitmap art);
	}

	public ArtistDetailImageTask(
			ArtistDetailImageLoader imageLoader,
			OnImageLoadedCallback onImageLoadedCallback) {
		this.imageLoader = imageLoader;
		this.onImageLoadedCallback = onImageLoadedCallback;
	}

	@Override
	protected Bitmap doInBackground(String... title) {
		return imageLoader.getImageFromApi(title[0]);
	}

	@Override
	protected void onPostExecute(Bitmap image) {
		onImageLoadedCallback.OnImageLoaded(image);
	}
}
