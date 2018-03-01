package com.edavtyan.materialplayer.ui.lists.track_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SourceSharedViews;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;

import javax.inject.Inject;

public class TrackListFragment extends ListFragment implements TrackListView {

	@Inject Navigator navigator;
	@Inject TrackListPresenter presenter;
	@Inject TrackListAdapter adapter;
	@Inject SharedTransitionsManager transitionsManager;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}

	@Override
	public void gotoNowPlaying() {
		SourceSharedViews sharedViews = new SourceSharedViews(getActivity());
		sharedViews.setOnExitAnimationEndListener(presenter::onExitAnimationEnd);
		transitionsManager.pushSourceViews(sharedViews);

		navigator.gotoNowPlaying(getActivity(), sharedViews.build());
	}

	protected TrackListDIComponent getComponent() {
		return DaggerTrackListDIComponent
				.builder()
				.appDIComponent(((App)getContext().getApplicationContext()).getAppComponent())
				.trackListDIModule(new TrackListDIModule(getActivity(), this))
				.build();
	}
}
