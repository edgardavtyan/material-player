package com.edavtyan.materialplayer.components.album_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.ListFragment;

public class AlbumListFragment
		extends ListFragment<AlbumListMvp.Presenter>
		implements AlbumListMvp.View {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AlbumListDI albumListDI = app.getAlbumListDI(getActivity(), this);
		initListView(albumListDI.providePresenter(), albumListDI.provideAdapter());
	}

	@Override
	public void goToAlbumDetail(int albumId) {
		AlbumDetailActivity.startActivity(getActivity(), albumId);
	}
}
