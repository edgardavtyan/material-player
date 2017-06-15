package com.edavtyan.materialplayer.components.search.album;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class SearchAlbumFactory extends BaseFactory {
	private final DBModule dbModule;
	private final SearchAlbumFragment view;
	private SearchAlbumModel model;
	private SearchAlbumPresenter presenter;
	private SearchAlbumAdapter adapter;

	public SearchAlbumFactory(Context context, SearchAlbumFragment view) {
		super(context);
		this.view = view;
		this.dbModule = new DBModule(context);
	}

	public SearchAlbumModel getModel() {
		if (model == null)
			model = new SearchAlbumModel(dbModule.getAlbumDB());
		return model;
	}

	public SearchAlbumFragment getView() {
		return view;
	}

	public SearchAlbumAdapter getAdapter() {
		if (adapter == null)
			adapter = new SearchAlbumAdapter(getContext());
		return adapter;
	}

	public SearchAlbumPresenter getPresenter() {
		if (presenter == null)
			presenter = new SearchAlbumPresenter(getModel(), getView());
		return presenter;
	}
}
