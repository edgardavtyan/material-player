package com.edavtyan.materialplayer.components.search.album;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.album_list.AlbumListFragment;
import com.edavtyan.materialplayer.components.lists.album_list.AlbumListModule;
import com.edavtyan.materialplayer.components.search.base.SearchView;
import com.edavtyan.materialplayer.components.search.base.SearchViewImpl;

import javax.inject.Inject;

public class SearchAlbumFragment extends AlbumListFragment implements SearchView {

	@Inject SearchViewImpl searchViewImpl;
	@Inject SearchAlbumPresenter presenter;
	@Inject SearchAlbumAdapter adapter;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_list_search;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent2().inject(this);
		initListView(presenter, adapter);
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

	protected SearchAlbumComponent getComponent2() {
		return DaggerSearchAlbumComponent
				.builder()
				.appComponent(((App)getContext().getApplicationContext()).getAppComponent())
				.searchAlbumModule(new SearchAlbumModule(this))
				.albumListModule(new AlbumListModule(getActivity(), this))
				.build();
	}
}
