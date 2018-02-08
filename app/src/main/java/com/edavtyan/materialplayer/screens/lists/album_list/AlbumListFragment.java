package com.edavtyan.materialplayer.screens.lists.album_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.lists.lib.ListFragment;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import javax.inject.Inject;

public class AlbumListFragment extends ListFragment implements AlbumListView {

	@Inject Navigator navigator;
	@Inject AlbumListPresenter presenter;
	@Inject AlbumListAdapter adapter;
	@Inject SharedTransitionsManager transitionsManager;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}

	@Override
	public void gotoAlbumDetailNormal(int albumId) {
		navigator.gotoAlbumDetailNormal(albumId);
	}

	@Override
	public void gotoAlbumDetailCompact(int albumId, SourceSharedViews sharedViews) {
		transitionsManager.pushSharedViews(sharedViews);
		navigator.gotoAlbumDetailCompact(getActivity(), albumId, sharedViews.build());
	}

	protected AlbumListComponent getComponent() {
		return DaggerAlbumListComponent
				.builder()
				.appComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.albumListFactory(new AlbumListFactory(getActivity(), this))
				.build();
	}
}
