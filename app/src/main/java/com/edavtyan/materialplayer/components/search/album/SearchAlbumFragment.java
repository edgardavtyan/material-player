package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.album_all.AlbumListMvp;
import com.edavtyan.materialplayer.components.search.base.SearchFragment;

public class SearchAlbumFragment extends SearchFragment implements AlbumListMvp.View {

	private SearchAlbumAdapter adapter;
	private SearchAlbumPresenter presenter;
	private Navigator navigator;

	@Override
	protected void onPostCreateView() {
		SearchAlbumFactory factory = app.getSearchAlbumFactory(getContext(), this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		navigator = factory.getNavigator();
		setAdapter(adapter);
	}

	public void updateData() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}
}
