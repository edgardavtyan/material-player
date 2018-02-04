package com.edavtyan.materialplayer.screens.lists.track_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.lists.lib.ListFragment;

import javax.inject.Inject;

public class TrackListFragment extends ListFragment implements TrackListView {

	@Inject Navigator navigator;
	@Inject TrackListPresenter presenter;
	@Inject TrackListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}

	@Override
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying(getActivity(), new Bundle());
	}

	protected TrackListComponent getComponent() {
		return DaggerTrackListComponent
				.builder()
				.appComponent(((App)getContext().getApplicationContext()).getAppComponent())
				.trackListFactory(new TrackListFactory(getActivity(), this))
				.build();
	}
}
