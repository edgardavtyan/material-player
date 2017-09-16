package com.edavtyan.materialplayer.components.album_all;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.modular.fragment.ListFragment;

public class AlbumListFragment extends ListFragment implements AlbumListMvp.View {

	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App app = (App) getContext().getApplicationContext();
		AlbumListFactory factory = app.getAlbumListDI(getActivity(), this);
		navigator = factory.getNavigator();
		initListView(factory.getPresenter(), factory.getAdapter());
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}
}
