package com.edavtyan.materialplayer.components.lists.album_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.lists.lib.ListFragment;

public class AlbumListFragment extends ListFragment implements AlbumListView {

	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AlbumListFactory factory = getApp().getAlbumListDI(getActivity(), this);
		navigator = factory.getNavigator();
		initListView(factory.getPresenter(), factory.getAdapter());
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}
}
