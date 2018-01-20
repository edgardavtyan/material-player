package com.edavtyan.materialplayer.screens.search.album;

import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListPresenter;
import com.edavtyan.materialplayer.screens.search.base.SearchPresenter;
import com.edavtyan.materialplayer.screens.search.base.SearchPresenterImpl;

public class SearchAlbumPresenter extends AlbumListPresenter implements SearchPresenter {
	private final SearchPresenterImpl searchPresenterImpl;

	public SearchAlbumPresenter(SearchAlbumModel model, SearchAlbumFragment view) {
		super(model, view);
		this.searchPresenterImpl = new SearchPresenterImpl(model, view);
	}

	public void onSearchChange(String query) {
		searchPresenterImpl.onSearchChange(query);
	}
}
