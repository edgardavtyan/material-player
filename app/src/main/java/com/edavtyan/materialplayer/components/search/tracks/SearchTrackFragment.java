package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.components.search.base.SearchFragment;
import com.edavtyan.materialplayer.db.Track;

import java.util.List;

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

	public void updateTracks(List<Track> tracks) {
		adapter.updateData(tracks);
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}
}
