package com.edavtyan.materialplayer.components.search.tracks;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class SearchTrackFactory extends BaseFactory {
	private final DBModule dbModule;
	private final SearchTrackFragment view;
	private SearchTrackModel model;
	private SearchTrackPresenter presenter;
	private SearchTrackAdapter adapter;

	public SearchTrackFactory(Context context, SearchTrackFragment view) {
		super(context);
		this.view = view;
		this.dbModule = new DBModule(context);
	}

	public SearchTrackModel getModel() {
		if (model == null)
			model = new SearchTrackModel(dbModule.getTrackDB());
		return model;
	}

	public SearchTrackFragment getView() {
		return view;
	}

	public SearchTrackAdapter getAdapter() {
		if (adapter == null)
			adapter = new SearchTrackAdapter(getContext());
		return adapter;
	}

	public SearchTrackPresenter getPresenter() {
		if (presenter == null)
			presenter = new SearchTrackPresenter(getModel(), getView());
		return presenter;
	}
}
