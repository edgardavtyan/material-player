package com.edavtyan.materialplayer.ui.search.album;

import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListPresenter;
import com.edavtyan.materialplayer.ui.search.base.SearchPresenter;
import com.edavtyan.materialplayer.ui.search.base.SearchPresenterImpl;

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
