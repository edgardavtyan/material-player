package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.components.lists.album_list.AlbumListPresenter;
import com.edavtyan.materialplayer.components.search.base.SearchPresenter;
import com.edavtyan.materialplayer.components.search.base.SearchPresenterImpl;

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
