package com.edavtyan.materialplayer.screens.search.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.lists.artist_list.ArtistListFragment;
import com.edavtyan.materialplayer.screens.lists.artist_list.ArtistListModule;
import com.edavtyan.materialplayer.screens.search.base.SearchView;
import com.edavtyan.materialplayer.screens.search.base.SearchViewImpl;

import javax.inject.Inject;

public class SearchArtistFragment extends ArtistListFragment implements SearchView {

	@Inject SearchViewImpl searchViewImpl;
	@Inject SearchArtistPresenter presenter;
	@Inject SearchArtistAdapter adapter;

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

	@Override
	public void showEmptyQuery() {
		searchViewImpl.showEmptyQuery();
	}

	@Override
	public void showEmptyResult() {
		searchViewImpl.showEmptyResult();
	}

	protected SearchArtistComponent getComponent2() {
		return DaggerSearchArtistComponent
				.builder()
				.appComponent(((App)getContext().getApplicationContext()).getAppComponent())
				.searchArtistModule(new SearchArtistModule(this))
				.artistListModule(new ArtistListModule(getActivity(), this))
				.build();
	}
}
