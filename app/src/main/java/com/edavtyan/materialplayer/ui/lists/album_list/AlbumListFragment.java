package com.edavtyan.materialplayer.ui.lists.album_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;
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
	public void gotoAlbumDetail(int albumId, SourceSharedViews sharedViews) {
		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoAlbumDetail(getActivity(), albumId, sharedViews.build());
	}

	protected AlbumListDIComponent getComponent() {
		return DaggerAlbumListDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.albumListDIModule(new AlbumListDIModule(getActivity(), this))
				.build();
	}
}
