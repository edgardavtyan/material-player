package com.edavtyan.materialplayer.components.search.tracks;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.search.SearchQueryInit;
import com.edavtyan.materialplayer.components.track_all.TrackListFragment;

public class SearchTrackFragment extends TrackListFragment {

	private SearchQueryInit searchQueryInit;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SearchTrackFactory factory = app.getSearchTrackFactory(getContext(), this);
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
