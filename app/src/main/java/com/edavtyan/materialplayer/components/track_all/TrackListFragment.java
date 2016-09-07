package com.edavtyan.materialplayer.components.track_all;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.mvp.list.ListFragment;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingActivity;

public class TrackListFragment
		extends ListFragment<TrackListMvp.Presenter>
		implements TrackListMvp.View {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TrackListDI trackListDI = app.getTrackListDI(getContext(), this);
		initListView(trackListDI.providePresenter(), trackListDI.provideAdapter());
	}

	@Override
	public void goToNowPlaying() {
		NowPlayingActivity.startActivity(getActivity());
	}
}
