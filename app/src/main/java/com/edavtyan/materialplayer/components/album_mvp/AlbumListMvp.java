package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.components.ListPresenter;

public interface AlbumListMvp {
	interface Presenter extends ListPresenter<AlbumListViewHolder> {
		void onItemClicked(int albumId);
		void addToPlaylist(int albumId);
	}

	interface Model {
		Album getAlbumAtIndex(int index);
		int getAlbumsCount();
		void addToPlaylist(int albumId);
		void update();
		void bindService();
		void unbindService();
	}

	interface View {
		void goToAlbumDetail(int albumId);
	}
}
