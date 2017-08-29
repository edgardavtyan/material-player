package com.edavtyan.materialplayer.components.track_all;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.mvp.list.ListFragment;

public class TrackListFragment
		extends ListFragment<TrackListMvp.Presenter>
		implements TrackListMvp.View {

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
