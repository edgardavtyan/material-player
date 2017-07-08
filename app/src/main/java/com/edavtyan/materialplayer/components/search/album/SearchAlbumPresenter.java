package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.components.album_all.AlbumListPresenter;

public class SearchAlbumPresenter extends AlbumListPresenter {
	private final SearchAlbumModel model;
	private final SearchAlbumFragment view;

	public SearchAlbumPresenter(SearchAlbumModel model, SearchAlbumFragment view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		model.setArtistTitle(query);
		model.update();
		view.updateData();
	}
}
