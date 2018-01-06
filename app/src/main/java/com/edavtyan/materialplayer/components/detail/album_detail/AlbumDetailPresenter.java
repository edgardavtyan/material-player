package com.edavtyan.materialplayer.components.detail.album_detail;

import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListPresenter;
import com.edavtyan.materialplayer.db.Album;

public class AlbumDetailPresenter
		extends TrackListPresenter
		implements ParallaxHeaderListPresenter {

	private final AlbumDetailView view;
	private final AlbumDetailModel model;

	public AlbumDetailPresenter(AlbumDetailModel model, AlbumDetailView view) {
		super(view, model);
		this.view = view;
		this.model = model;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Album album = model.getAlbum();
		view.setAlbumTitle(album.getTitle());
		view.setAlbumInfo(
				album.getArtistTitle(),
				album.getTracksCount(),
				model.getTotalAlbumDuration());
		view.setAlbumImage(model.getAlbumArt());
	}
}
