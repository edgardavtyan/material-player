package com.edavtyan.materialplayer.components.album_detail;

import android.content.Context;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.ListFactory;

public class AlbumDetailFactory extends ListFactory {
	private final AlbumDetailMvp.View view;
	private final int albumId;
	private AlbumDetailAdapter adapter;
	private AlbumDetailMvp.Presenter presenter;
	private AlbumDetailMvp.Model model;
	private AlbumDB albumDB;
	private TrackDB trackDB;

	public AlbumDetailFactory(Context context, AlbumDetailActivity view, int albumId) {
		super(context);
		this.view = view;
		this.albumId = albumId;
	}

	public AlbumDetailMvp.View getView() {
		return view;
	}

	public AlbumDetailMvp.Model getModel() {
		if (model == null)
			model = new AlbumDetailModel(
					getContext(),
					getAlbumDB(),
					getTrackDB(),
					getArtProvider(),
					getCompactListPref(),
					albumId);

		return model;
	}

	public AlbumDB getAlbumDB() {
		if (albumDB == null)
			albumDB = new AlbumDB(getContext());
		return albumDB;
	}

	public TrackDB getTrackDB() {
		if (trackDB == null)
			trackDB = new TrackDB(getContext());
		return trackDB;
	}

	public AlbumDetailAdapter getAdapter() {
		if (adapter == null)
			adapter = new AlbumDetailAdapter(getContext(), getPresenter());
		return adapter;
	}

	public AlbumDetailMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new AlbumDetailPresenter(getModel(), getView());
		return presenter;
	}
}
