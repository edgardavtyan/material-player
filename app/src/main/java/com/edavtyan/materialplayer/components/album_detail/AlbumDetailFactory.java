package com.edavtyan.materialplayer.components.album_detail;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class AlbumDetailFactory extends BaseFactory {
	private final AlbumDetailMvp.View view;
	private final int albumId;
	private AlbumDetailAdapter adapter;
	private AlbumDetailMvp.Presenter presenter;
	private AlbumDetailMvp.Model model;
	private ArtistDB artistDB;
	private AlbumDB albumDB;
	private TrackDB trackDB;

	public AlbumDetailFactory(AlbumDetailActivity view, int albumId) {
		super(view);
		this.view = view;
		this.albumId = albumId;
	}

	public AlbumDetailMvp.View provideView() {
		return view;
	}

	private AlbumDetailMvp.Model provideModel() {
		if (model == null)
			model = new AlbumDetailModel(
					provideContext(),
					provideArtistDB(),
					provideAlbumDB(),
					provideTrackDB(),
					albumId);

		return model;
	}

	private ArtistDB provideArtistDB() {
		if (artistDB == null)
			artistDB = new ArtistDB(provideContext());
		return artistDB;
	}

	private AlbumDB provideAlbumDB() {
		if (albumDB == null)
			albumDB = new AlbumDB(provideContext());
		return albumDB;
	}

	private TrackDB provideTrackDB() {
		if (trackDB == null)
			trackDB = new TrackDB(provideContext());
		return trackDB;
	}

	public AlbumDetailAdapter provideAdapter() {
		if (adapter == null)
			adapter = new AlbumDetailAdapter(provideContext(), providePresenter());
		return adapter;
	}

	public AlbumDetailMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new AlbumDetailPresenter(provideModel(), provideView());
		return presenter;
	}
}
