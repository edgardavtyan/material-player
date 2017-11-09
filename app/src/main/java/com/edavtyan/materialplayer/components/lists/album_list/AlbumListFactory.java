package com.edavtyan.materialplayer.components.lists.album_list;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.components.lists.lib.ListFactory;

public class AlbumListFactory extends ListFactory {
	private final AlbumListMvp.View view;
	private final DBModule dbModule;
	private AlbumListModel model;
	private AlbumListPresenter presenter;
	private AlbumListAdapter adapter;

	public AlbumListFactory(Context context, AlbumListMvp.View view) {
		super(context);
		this.view = view;
		this.dbModule = new DBModule(context);
	}

	public AlbumListMvp.View getView() {
		return view;
	}

	public AlbumListMvp.Model getModel() {
		if (model == null)
			model = new AlbumListModel(
					getModelServiceModule(),
					dbModule.getAlbumDB(),
					dbModule.getTrackDB(),
					getCompactListPref());
		return model;
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
