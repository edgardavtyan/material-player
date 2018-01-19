package com.edavtyan.materialplayer.components.search.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListFragment;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListModule;
import com.edavtyan.materialplayer.components.search.base.SearchView;
import com.edavtyan.materialplayer.components.search.base.SearchViewImpl;
import com.edavtyan.materialplayer.lib.lastfm.LastFmFactory;

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
				.searchArtistModule(new SearchArtistModule(this))
				.artistListModule(new ArtistListModule(getActivity(), this))
				.lastFmFactory(new LastFmFactory(getString(R.string.lastfm_api_key)))
				.build();
	}
}
