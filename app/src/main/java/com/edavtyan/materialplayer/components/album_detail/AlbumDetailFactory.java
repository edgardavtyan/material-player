package com.edavtyan.materialplayer.components.album_detail;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class AlbumDetailFactory extends BaseFactory {
	private final AlbumDetailMvp.View view;
	private final int albumId;
	private AlbumDetailAdapter adapter;
	private AlbumDetailMvp.Presenter presenter;
	private AlbumDetailMvp.Model model;
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

	public AlbumDetailMvp.Model provideModel() {
		if (model == null)
			model = new AlbumDetailModel(
					provideContext(),
					provideAlbumDB(),
					provideTrackDB(),
					provideArtProvider(),
					providePrefs(),
					albumId);

		return model;
	}

	public AlbumDB provideAlbumDB() {
		if (albumDB == null)
			albumDB = new AlbumDB(provideContext());
		return albumDB;
	}

	public TrackDB provideTrackDB() {
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
