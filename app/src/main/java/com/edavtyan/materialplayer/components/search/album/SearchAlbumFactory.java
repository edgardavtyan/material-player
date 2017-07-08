package com.edavtyan.materialplayer.components.search.album;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;

public class SearchAlbumFactory extends BaseFactory {
	private final DBModule dbModule;
	private final SearchAlbumFragment view;
	private SearchAlbumModel model;
	private SearchAlbumPresenter presenter;
	private SearchAlbumAdapter adapter;
	private CompactListPref compactListPref;

	public SearchAlbumFactory(Context context, SearchAlbumFragment view) {
		super(context);
		this.view = view;
		this.dbModule = new DBModule(context);
	}

	public SearchAlbumModel getModel() {
		if (model == null)
			model = new SearchAlbumModel(getContext(), dbModule.getAlbumDB(), dbModule.getTrackDB(), getCompactListPref());
		return model;
	}

	public SearchAlbumFragment getView() {
		return view;
	}

	public SearchAlbumAdapter getAdapter() {
		if (adapter == null)
			adapter = new SearchAlbumAdapter(getContext(), getPresenter());
		return adapter;
	}

	public SearchAlbumPresenter getPresenter() {
		if (presenter == null)
			presenter = new SearchAlbumPresenter(getModel(), getView());
		return presenter;
	}

	public CompactListPref getCompactListPref() {
		if (compactListPref == null)
			compactListPref = new CompactListPref(getContext(), getPrefs());
		return compactListPref;
	}
}
