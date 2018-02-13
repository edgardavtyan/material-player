package com.edavtyan.materialplayer.ui.search.tracks;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListFactory;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListFragment;
import com.edavtyan.materialplayer.ui.search.base.SearchView;
import com.edavtyan.materialplayer.ui.search.base.SearchViewImpl;

import javax.inject.Inject;

public class SearchTrackFragment extends TrackListFragment implements SearchView {

	@Inject SearchViewImpl searchViewImpl;
	@Inject SearchTrackPresenter presenter;
	@Inject SearchTrackAdapter adapter;

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

	protected SearchTrackComponent getComponent2() {
		return DaggerSearchTrackComponent
				.builder()
				.appComponent(((App)getContext().getApplicationContext()).getAppComponent())
				.searchTrackModule(new SearchTrackModule(this))
				.trackListFactory(new TrackListFactory(getActivity(), this))
				.build();
	}
}
