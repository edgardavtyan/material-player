package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.components.search.base.SearchFragment;

public class SearchTrackFragment extends SearchFragment {

	private SearchTrackAdapter adapter;
	private SearchTrackPresenter presenter;

	@Override
	protected void onPostCreateView() {
		SearchTrackFactory factory = app.getSearchTrackFactory(getContext(), this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		setAdapter(adapter);
	}

	public void updateTracks() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}
}
