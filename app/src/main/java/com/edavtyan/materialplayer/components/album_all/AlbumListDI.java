package com.edavtyan.materialplayer.components.album_all;

import android.content.Context;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.base.BaseDI;

public class AlbumListDI extends BaseDI {
	private final Context context;
	private AlbumListModel model;
	private AlbumListMvp.View view;
	private AlbumListPresenter presenter;
	private AlbumListAdapter adapter;
	private AlbumDB albumDB;
	private TrackDB trackDB;

	public AlbumListDI(Context context, AlbumListMvp.View view) {
		super(context);
		this.context = context;
		this.view = view;
	}

	public AlbumListMvp.Model provideModel() {
		if (model == null) model = new AlbumListModel(context, provideAlbumDB(), provideTrackDB());
		return model;
	}

	public AlbumDB provideAlbumDB() {
		if (albumDB == null) albumDB = new AlbumDB(context);
		return new AlbumDB(context);
	}

	public TrackDB provideTrackDB() {
		if (trackDB == null) trackDB = new TrackDB(context);
		return new TrackDB(context);
	}

	public AlbumListMvp.Presenter providePresenter() {
		if (presenter == null) presenter = new AlbumListPresenter(provideModel(), provideView());
		return presenter;
	}

	public AlbumListMvp.View provideView() {
		return view;
	}

	public AlbumListAdapter provideAdapter() {
		if (adapter == null) adapter = new AlbumListAdapter(context, providePresenter());
		return adapter;
	}
}
