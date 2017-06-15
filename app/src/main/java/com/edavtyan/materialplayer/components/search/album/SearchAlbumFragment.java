package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.components.search.base.SearchFragment;
import com.edavtyan.materialplayer.db.Album;

import java.util.List;

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

	public void updateAlbums(List<Album> albums) {
		adapter.updateData(albums);
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}
}
