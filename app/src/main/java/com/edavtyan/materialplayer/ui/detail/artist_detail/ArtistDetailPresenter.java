package com.edavtyan.materialplayer.ui.detail.artist_detail;

import com.edavtyan.materialplayer.db.types.Artist;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListPresenter;

public class ArtistDetailPresenter
		extends AlbumListPresenter
		implements ParallaxHeaderListPresenter {

	private final ArtistDetailModel model;
	private final ArtistDetailActivity view;

	public ArtistDetailPresenter(ArtistDetailModel model, ArtistDetailActivity activity) {
		super(model, activity);
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

	public void onSortByName() {
		model.sortByName();
		view.notifyDataSetChanged();
	}

	public void onSortByDate() {
		model.sortByDate();
		view.notifyDataSetChanged();
	}
}
