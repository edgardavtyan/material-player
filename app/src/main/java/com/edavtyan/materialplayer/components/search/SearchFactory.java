package com.edavtyan.materialplayer.components.search;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class SearchFactory extends BaseFactory {
	private final DBModule dbModule;
	private final SearchActivity view;
	private SearchModel model;
	private SearchPresenter presenter;
	private SearchAdapter adapter;

	public SearchFactory(Context context, SearchActivity view) {
		super(context);
		this.view = view;
		this.dbModule = new DBModule(context);
	}

	public SearchModel getModel() {
		if (model == null)
			model = new SearchModel(dbModule.getArtistDB());
		return model;
	}

	public SearchActivity getView() {
		return view;
	}

	public SearchAdapter getAdapter() {
		if (adapter == null)
			adapter = new SearchAdapter(getContext());
		return adapter;
	}

	public SearchPresenter getPresenter() {
		if (presenter == null)
			presenter = new SearchPresenter(getModel(), getView());
		return presenter;
	}
}
