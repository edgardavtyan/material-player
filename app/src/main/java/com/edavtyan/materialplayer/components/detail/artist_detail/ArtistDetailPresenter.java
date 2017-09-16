package com.edavtyan.materialplayer.components.detail.artist_detail;

import com.edavtyan.materialplayer.components.lists.album_list.AlbumListPresenter;
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
		model.loadArtistImage(view::setArtistImage); // TODO: Unit test this
	}
}
