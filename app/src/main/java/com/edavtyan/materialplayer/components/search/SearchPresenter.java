package com.edavtyan.materialplayer.components.search;

public class SearchPresenter {
	private final SearchModel model;
	private final SearchActivity view;

	public SearchPresenter(SearchModel model, SearchActivity view) {
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		view.updateSearchResults(model.searchArtists(query));
	}
}
