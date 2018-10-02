package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;

import javax.inject.Inject;

public class PlaylistListFragment extends ListFragment {
	@Inject PlaylistListPresenter presenter;
	@Inject PlaylistListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}

	private PlaylistListDIComponent getComponent() {
		return DaggerPlaylistListDIComponent
				.builder()
				.appDIComponent(((App)getContext().getApplicationContext()).getAppComponent())
				.playlistListDIModule(new PlaylistListDIModule(this))
				.build();
	}
}
