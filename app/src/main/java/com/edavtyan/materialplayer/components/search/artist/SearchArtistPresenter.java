package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.artist_all.ArtistListPresenter;
import com.edavtyan.materialplayer.components.search.base.SearchPresenter;

public class SearchArtistPresenter extends ArtistListPresenter {
	private final SearchArtistModel model;
	private final SearchArtistFragment view;

	public SearchArtistPresenter(SearchArtistModel model, SearchArtistFragment view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		model.setArtistTitle(query);
		model.update();
		view.updateArtists();
	}
}
