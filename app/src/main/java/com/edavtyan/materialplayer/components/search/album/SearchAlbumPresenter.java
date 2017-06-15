package com.edavtyan.materialplayer.components.search.album;

public class SearchAlbumPresenter {
	private final SearchAlbumModel model;
	private final SearchAlbumFragment view;

	public SearchAlbumPresenter(SearchAlbumModel model, SearchAlbumFragment view) {
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		view.updateAlbums(model.searchAlbums(query));
	}
}
