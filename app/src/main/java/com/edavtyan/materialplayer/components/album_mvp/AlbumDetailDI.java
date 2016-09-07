package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.lib.db.AlbumDB;
import com.edavtyan.materialplayer.lib.db.ArtistDB;
import com.edavtyan.materialplayer.lib.db.TrackDB;

public class AlbumDetailDI {
	private final AlbumDetailActivity view;
	private final int albumId;
	private AlbumDetailAdapter adapter;
	private AlbumDetailMvp.Presenter presenter;
	private AlbumDetailMvp.Model model;
	private ArtistDB artistDB;
	private AlbumDB albumDB;
	private TrackDB trackDB;

	public AlbumDetailDI(AlbumDetailActivity view, int albumId) {
		this.view = view;
		this.albumId = albumId;
	}

	private AlbumDetailMvp.Model provideModel() {
		if (model == null) {
			model = new AlbumDetailModel(
					view,
					provideArtistDB(),
					provideAlbumDB(),
					provideTrackDB(),
					albumId);
		}

		return model;
	}

	private ArtistDB provideArtistDB() {
		if (artistDB == null) artistDB = new ArtistDB(view);
		return artistDB;
	}

	private AlbumDB provideAlbumDB() {
		if (albumDB == null) albumDB = new AlbumDB(view);
		return albumDB;
	}

	private TrackDB provideTrackDB() {
		if (trackDB == null) trackDB = new TrackDB(view);
		return trackDB;
	}

	public AlbumDetailAdapter provideAdapter() {
		if (adapter == null) adapter = new AlbumDetailAdapter(view, providePresenter());
		return adapter;
	}

	public AlbumDetailMvp.Presenter providePresenter() {
		if (presenter == null) presenter = new AlbumDetailPresenter(view, provideModel());
		return presenter;
	}
}
