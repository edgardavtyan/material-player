package com.edavtyan.materialplayer.components.album_all;

import android.content.Context;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class AlbumListFactory extends BaseFactory {
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

	public AlbumListMvp.View provideView() {
		return view;
	}

	public AlbumListMvp.Model provideModel() {
		if (model == null)
			model = new AlbumListModel(provideContext(), provideAlbumDB(), provideTrackDB());
		return model;
	}

	public AlbumDB provideAlbumDB() {
		if (albumDB == null)
			albumDB = new AlbumDB(provideContext());
		return new AlbumDB(provideContext());
	}

	public TrackDB provideTrackDB() {
		if (trackDB == null)
			trackDB = new TrackDB(provideContext());
		return new TrackDB(provideContext());
	}

	public AlbumListMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new AlbumListPresenter(provideModel(), provideView());
		return presenter;
	}

	public AlbumListAdapter provideAdapter() {
		if (adapter == null)
			adapter = new AlbumListAdapter(provideContext(), providePresenter());
		return adapter;
	}
}
