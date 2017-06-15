package com.edavtyan.materialplayer.components.search;

public class SearchArtistPresenter {
	private final SearchArtistModel model;
	private final SearchArtistFragment view;

	public SearchArtistPresenter(SearchArtistModel model, SearchArtistFragment view) {
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		view.updateArtists(model.searchArtists(query));
	}
}
