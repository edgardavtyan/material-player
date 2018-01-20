package com.edavtyan.materialplayer.screens.search.tracks;

import com.edavtyan.materialplayer.screens.search.base.SearchPresenter;
import com.edavtyan.materialplayer.screens.search.base.SearchPresenterImpl;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListPresenter;

public class SearchTrackPresenter extends TrackListPresenter implements SearchPresenter {
	private final SearchPresenterImpl searchPresenterImpl;

	public SearchTrackPresenter(SearchTrackModel model, SearchTrackFragment view) {
		super(view, model);
		this.searchPresenterImpl = new SearchPresenterImpl(model, view);
	}

	public void onSearchChange(String query) {
		searchPresenterImpl.onSearchChange(query);
	}
}
