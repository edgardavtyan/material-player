package com.edavtyan.materialplayer.screens.detail.artist_detail;

import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListPresenter;
import com.edavtyan.materialplayer.db.Artist;

public class ArtistDetailPresenter
		extends AlbumListPresenter
		implements ParallaxHeaderListPresenter {

	private final ArtistDetailModel model;
	private final ArtistDetailView view;

	public ArtistDetailPresenter(ArtistDetailModel model, ArtistDetailView view) {
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
		model.loadArtistImage(view::setArtistImage); // TODO: Unit test this
	}
}
