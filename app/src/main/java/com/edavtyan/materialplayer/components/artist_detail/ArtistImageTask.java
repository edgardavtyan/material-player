package com.edavtyan.materialplayer.components.artist_detail;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.edavtyan.materialplayer.utils.ArtistArtProvider;

public class ArtistImageTask extends AsyncTask<String, Void, Bitmap> {

	private final ArtistArtProvider artistArtProvider;
	private final ArtistDetailMvp.Model.OnArtLoadedListener onArtLoadedListener;

	public ArtistImageTask(
			ArtistArtProvider artistArtProvider,
			ArtistDetailMvp.Model.OnArtLoadedListener onArtLoadedListener) {
		this.artistArtProvider = artistArtProvider;
		this.onArtLoadedListener = onArtLoadedListener;
	}

	@Override
	protected Bitmap doInBackground(String... title) {
		return artistArtProvider.load(title[0]);
	}

	@Override
	protected void onPostExecute(Bitmap art) {
		if (onArtLoadedListener != null) onArtLoadedListener.onArtistImageLoaded(art);
	}
}
