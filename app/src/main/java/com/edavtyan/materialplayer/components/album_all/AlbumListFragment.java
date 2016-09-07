package com.edavtyan.materialplayer.components.album_all;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.mvp.list.ListFragment;

public class AlbumListFragment
		extends ListFragment<AlbumListMvp.Presenter>
		implements AlbumListMvp.View {

	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AlbumListDI albumListDI = app.getAlbumListDI(getActivity(), this);
		initListView(albumListDI.providePresenter(), albumListDI.provideAdapter());
		navigator = albumListDI.provideNavigator();
	}

	@Override
	public void goToAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}
}
