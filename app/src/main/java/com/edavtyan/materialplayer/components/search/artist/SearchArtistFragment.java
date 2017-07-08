package com.edavtyan.materialplayer.components.search.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.artist_all.ArtistListFragment;
import com.edavtyan.materialplayer.components.search.SearchQueryInit;

public class SearchArtistFragment extends ArtistListFragment {

	private SearchQueryInit searchQueryInit;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SearchArtistFactory factory = app.getSearchArtistFactory(getContext(), this);
		initListView(factory.getPresenter(), factory.getAdapter());
		searchQueryInit = new SearchQueryInit(this, factory.getPresenter());
	}

	@Override
	public void onStart() {
		super.onStart();
		searchQueryInit.init();
	}

	@Override
	public void onStop() {
		super.onStop();
		searchQueryInit.destroy();
	}
}
