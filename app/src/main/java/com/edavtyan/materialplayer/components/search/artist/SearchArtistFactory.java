package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class SearchArtistFactory extends BaseFactory {
	private final DBModule dbModule;
	private final SearchArtistFragment view;
	private SearchArtistModel model;
	private SearchArtistPresenter presenter;
	private SearchArtistAdapter adapter;

	public SearchArtistFactory(Context context, SearchArtistFragment view) {
		super(context);
		this.view = view;
		this.dbModule = new DBModule(context);
	}

	public SearchArtistModel getModel() {
		if (model == null)
			model = new SearchArtistModel(dbModule.getArtistDB());
		return model;
	}

	public SearchArtistFragment getView() {
		return view;
	}

	public SearchArtistAdapter getAdapter() {
		if (adapter == null)
			adapter = new SearchArtistAdapter(getContext(), getPresenter());
		return adapter;
	}

	public SearchArtistPresenter getPresenter() {
		if (presenter == null)
			presenter = new SearchArtistPresenter(getModel(), getView());
		return presenter;
	}
}
