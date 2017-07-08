package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.search.base.SearchFragment;
import com.edavtyan.materialplayer.components.track_all.TrackListMvp;

public class SearchTrackFragment extends SearchFragment implements TrackListMvp.View {

	private SearchTrackAdapter adapter;
	private SearchTrackPresenter presenter;
	private Navigator navigator;

	@Override
	protected void onPostCreateView() {
		SearchTrackFactory factory = app.getSearchTrackFactory(getContext(), this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		navigator = factory.getNavigator();
		setAdapter(adapter);
	}

	public void updateTracks() {
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
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}
}
