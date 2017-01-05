package com.edavtyan.materialplayer.components.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.album_all.AlbumListPresenter;
import com.edavtyan.materialplayer.db.Artist;

public class ArtistDetailPresenter
		extends AlbumListPresenter
		implements ArtistDetailMvp.Presenter {

	private final ArtistDetailMvp.Model model;
	private final ArtistDetailMvp.View view;

	public ArtistDetailPresenter(ArtistDetailMvp.Model model, ArtistDetailMvp.View view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Artist artist = model.getArtist();
		view.setArtistTitle(artist.getTitle());
		view.setArtistInfo(artist.getAlbumsCount(), artist.getTracksCount());

		//TODO: Find how to unit test this
		Bitmap localArt = model.getLocalArtistImage();
		if (localArt == null) {
			model.loadArtistImageFromApi(view::setArtistImage);
		} else {
			view.setArtistImage(localArt);
		}
	}
}
