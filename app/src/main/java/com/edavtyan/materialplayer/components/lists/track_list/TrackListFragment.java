package com.edavtyan.materialplayer.components.lists.track_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.lists.lib.ListFragment;

public class TrackListFragment extends ListFragment implements TrackListView {

	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TrackListFactory factory = app.getTrackListDI(getContext(), this);
		initListView(factory.getPresenter(), factory.getAdapter());
		navigator = factory.getNavigator();
	}

	@Override
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}
}
