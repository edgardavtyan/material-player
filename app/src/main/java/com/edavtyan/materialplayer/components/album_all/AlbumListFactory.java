package com.edavtyan.materialplayer.components.album_all;

import android.content.Context;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.ListFactory;

public class AlbumListFactory extends ListFactory {
	private final AlbumListMvp.View view;
	private AlbumListModel model;
	private AlbumListPresenter presenter;
	private AlbumListAdapter adapter;
	private AlbumDB albumDB;
	private TrackDB trackDB;

	public AlbumListFactory(Context context, AlbumListMvp.View view) {
		super(context);
		this.view = view;
	}

	public AlbumListMvp.View getView() {
		return view;
	}

	public AlbumListMvp.Model getModel() {
		if (model == null)
			model = new AlbumListModel(
					getContext(),
					getAlbumDB(),
					getTrackDB(),
					getCompactListPref());
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

	public AlbumListMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new AlbumListPresenter(getModel(), getView());
		return presenter;
	}

	public AlbumListAdapter getAdapter() {
		if (adapter == null)
			adapter = new AlbumListAdapter(getContext(), getPresenter());
		return adapter;
	}
}
