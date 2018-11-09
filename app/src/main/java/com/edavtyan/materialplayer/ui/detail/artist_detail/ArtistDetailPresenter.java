package com.edavtyan.materialplayer.ui.detail.artist_detail;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListPresenter;

public class ArtistDetailPresenter
		extends AlbumListPresenter
		implements ParallaxHeaderListPresenter {

	private final ArtistDetailModel model;
	private final ArtistDetailActivity view;

	public ArtistDetailPresenter(
			ArtistDetailModel model, ArtistDetailActivity activity, ThemeColors theme) {
		super(model, activity, theme);
		this.model = model;
		this.view = activity;
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
