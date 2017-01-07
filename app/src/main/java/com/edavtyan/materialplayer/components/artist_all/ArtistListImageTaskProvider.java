package com.edavtyan.materialplayer.components.artist_all;

public class ArtistListImageTaskProvider {
	public ArtistListImageTask create(
			ArtistListImageLoader imageLoader,
			ArtistListImageTask.Callback callback) {
		return new ArtistListImageTask(imageLoader, callback);
	}
}
