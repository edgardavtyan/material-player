package com.edavtyan.materialplayer.components.search.tracks;

public class SearchTrackPresenter {
	private final SearchTrackModel model;
	private final SearchTrackFragment view;

	public SearchTrackPresenter(SearchTrackModel model, SearchTrackFragment view) {
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		view.updateTracks(model.searchTracks(query));
	}
}
