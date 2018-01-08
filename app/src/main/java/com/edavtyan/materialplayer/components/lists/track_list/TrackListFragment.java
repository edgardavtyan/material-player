package com.edavtyan.materialplayer.components.lists.track_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.lists.lib.ListFragment;

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
		navigator.gotoNowPlaying();
	}

	protected TrackListComponent getComponent() {
		return DaggerTrackListComponent
				.builder()
				.trackListModule(new TrackListModule(getActivity(), this))
				.build();
	}
}
