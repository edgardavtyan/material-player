package com.edavtyan.materialplayer.components.search.album;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.album_list.AlbumListFragment;
import com.edavtyan.materialplayer.components.search.base.SearchView;
import com.edavtyan.materialplayer.components.search.base.SearchViewImpl;

public class SearchAlbumFragment extends AlbumListFragment implements SearchView {

	private SearchViewImpl searchViewImpl;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_list_search;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SearchAlbumFactory factory = getApp().getSearchAlbumFactory(getContext(), this);
		initListView(factory.getPresenter(), factory.getAdapter());
		searchViewImpl = new SearchViewImpl(this, factory.getPresenter());
	}

	@Override
	public void onStart() {
		super.onStart();
		searchViewImpl.init();
	}

	@Override
	public void onStop() {
		super.onStop();
		searchViewImpl.destroy();
	}

	public void showEmptyQuery() {
		searchViewImpl.showEmptyQuery();
	}

	public void showEmptyResult() {
		searchViewImpl.showEmptyResult();
	}
}
