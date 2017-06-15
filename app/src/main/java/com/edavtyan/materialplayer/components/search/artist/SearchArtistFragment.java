package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.search.base.SearchFragment;

public class SearchArtistFragment extends SearchFragment {

	private SearchArtistAdapter adapter;
	private SearchArtistPresenter presenter;

	@Override
	protected void onPostCreateView() {
		SearchArtistFactory factory = app.getSearchArtistFactory(getContext(), this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		setAdapter(adapter);
	}

	public void updateArtists() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}
}
