package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.artist_all.ArtistListMvp;
import com.edavtyan.materialplayer.components.search.base.SearchFragment;

public class SearchArtistFragment extends SearchFragment implements ArtistListMvp.View {

	private SearchArtistAdapter adapter;
	private SearchArtistPresenter presenter;
	private Navigator navigator;

	@Override
	protected void onPostCreateView() {
		SearchArtistFactory factory = app.getSearchArtistFactory(getContext(), this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		navigator = factory.getNavigator();
		setAdapter(adapter);
	}

	public void updateArtists() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void gotoArtistDetail(String title) {
		navigator.gotoArtistDetail(title);
	}
}
