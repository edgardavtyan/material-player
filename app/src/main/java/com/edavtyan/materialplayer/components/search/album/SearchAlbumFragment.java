package com.edavtyan.materialplayer.components.search.album;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.album_all.AlbumListFragment;
import com.edavtyan.materialplayer.components.album_all.AlbumListMvp;
import com.edavtyan.materialplayer.components.search.SearchQueryInit;

public class SearchAlbumFragment extends AlbumListFragment implements AlbumListMvp.View {

	private SearchQueryInit searchQueryInit;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SearchAlbumFactory factory = app.getSearchAlbumFactory(getContext(), this);
		initListView(factory.getPresenter(), factory.getAdapter());
		searchQueryInit = new SearchQueryInit(this, factory.getPresenter());
	}

	@Override
	public void onStart() {
		super.onStart();
		searchQueryInit.init();
	}

	@Override
	public void onStop() {
		super.onStop();
		searchQueryInit.destroy();
	}
}
