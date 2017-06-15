package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.search.base.SearchFragment;
import com.edavtyan.materialplayer.db.Artist;

import java.util.List;

public class SearchArtistFragment extends SearchFragment {

	private SearchArtistAdapter adapter;
	private SearchArtistPresenter presenter;

	@Override
	protected void onPostCreateView() {
		SearchArtistFactory factory = app.getSearchFactory(getContext(), this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		setAdapter(adapter);
	}

	public void updateArtists(List<Artist> artists) {
		adapter.updateData(artists);
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}
}
