package com.edavtyan.materialplayer.ui.search.tracks;

import com.edavtyan.materialplayer.ui.search.base.SearchPresenter;
import com.edavtyan.materialplayer.ui.search.base.SearchPresenterImpl;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListPresenter;

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
