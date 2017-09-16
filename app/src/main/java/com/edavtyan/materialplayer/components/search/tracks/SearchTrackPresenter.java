package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.components.search.base.SearchPresenter;
import com.edavtyan.materialplayer.components.search.base.SearchPresenterImpl;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListPresenter;

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
