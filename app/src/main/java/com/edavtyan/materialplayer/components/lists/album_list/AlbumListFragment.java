package com.edavtyan.materialplayer.components.lists.album_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.lists.lib.ListFragment;

import javax.inject.Inject;

public class AlbumListFragment extends ListFragment implements AlbumListView {

	@Inject Navigator navigator;
	@Inject AlbumListPresenter presenter;
	@Inject AlbumListAdapter adapter;

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
	public void gotoAlbumDetailCompact(int albumId) {
		navigator.gotoAlbumDetailCompact(albumId);
	}

	protected AlbumListComponent getComponent() {
		return DaggerAlbumListComponent
				.builder()
				.appComponent(((App)getContext().getApplicationContext()).getAppComponent())
				.albumListModule(new AlbumListModule(getActivity(), this))
				.build();
	}
}
