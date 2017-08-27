package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;

import com.edavtyan.materialplayer.components.artist_all.ArtistListFactory;
import com.edavtyan.materialplayer.db.DBModule;

public class SearchArtistFactory extends ArtistListFactory {
	private final DBModule dbModule;
	private final SearchArtistFragment view;
	private SearchArtistModel model;
	private SearchArtistPresenter presenter;
	private SearchArtistAdapter adapter;

	public SearchArtistFactory(Context context, SearchArtistFragment view) {
		super(context, view);
		this.view = view;
		this.dbModule = new DBModule(context);
	}

	public SearchArtistModel getModel() {
		if (model == null)
			model = new SearchArtistModel(
					getContext(),
					dbModule.getArtistDB(),
					dbModule.getTrackDB(),
					getImageLoader(),
					getCompactListPref());
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
