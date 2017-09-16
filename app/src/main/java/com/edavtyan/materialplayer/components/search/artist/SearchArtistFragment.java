package com.edavtyan.materialplayer.components.search.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.artist_all.ArtistListFragment;
import com.edavtyan.materialplayer.components.search.base.SearchViewImpl;
import com.edavtyan.materialplayer.components.search.base.SearchView;
import com.edavtyan.materialplayer.modular.fragment.ListFragmentModule;

public class SearchArtistFragment extends ArtistListFragment implements SearchView {

	private SearchViewImpl searchViewImpl;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_list_search;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SearchArtistFactory factory = app.getSearchArtistFactory(getContext(), this);
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

	@Override
	public void showEmptyQuery() {
		searchViewImpl.showEmptyQuery();
	}

	@Override
	public void showEmptyResult() {
		searchViewImpl.showEmptyResult();
	}
}
