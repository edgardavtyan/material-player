package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import javax.inject.Inject;

public class ArtistListFragment extends ListFragment implements ArtistListView {

	@Inject Navigator navigator;
	@Inject ArtistListPresenter presenter;
	@Inject ArtistListAdapter adapter;
	@Inject SharedTransitionsManager transitionsManager;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}


	@Override
	public void gotoArtistDetail(String title, SourceSharedViews sharedViews) {
		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoArtistDetailCompact(getActivity(), title, sharedViews.build());

	}

	protected ArtistListComponent getComponent() {
		return DaggerArtistListComponent
				.builder()
				.appComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.artistListFactory(new ArtistListFactory(getActivity(), this))
				.build();
	}
}
