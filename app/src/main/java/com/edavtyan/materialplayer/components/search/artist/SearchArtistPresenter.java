package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.artist_all.ArtistListPresenter;
import com.edavtyan.materialplayer.components.search.base.SearchPresenter;
import com.edavtyan.materialplayer.components.search.base.SearchPresenterImpl;

public class SearchArtistPresenter extends ArtistListPresenter implements SearchPresenter {
	private final SearchPresenterImpl searchPresenterImpl;

	public SearchArtistPresenter(SearchArtistModel model, SearchArtistFragment view) {
		super(model, view);
		this.searchPresenterImpl = new SearchPresenterImpl(model, view);
	}

	public void onSearchChange(String query) {
		searchPresenterImpl.onSearchChange(query);
	}
}
