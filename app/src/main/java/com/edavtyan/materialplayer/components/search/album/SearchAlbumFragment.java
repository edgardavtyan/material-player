package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.components.search.base.SearchFragment;

public class SearchAlbumFragment extends SearchFragment {

	private SearchAlbumAdapter adapter;
	private SearchAlbumPresenter presenter;

	@Override
	protected void onPostCreateView() {
		SearchAlbumFactory factory = app.getSearchAlbumFactory(getContext(), this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		setAdapter(adapter);
	}

	public void updateData() {
		adapter.updateData();
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}
}
