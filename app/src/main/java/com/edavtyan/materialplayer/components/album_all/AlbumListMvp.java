package com.edavtyan.materialplayer.components.album_all;

import com.edavtyan.materialplayer.lib.mvp.list.ListPresenter;
import com.edavtyan.materialplayer.db.Album;

@SuppressWarnings("unused")
public interface AlbumListMvp {
	interface Presenter extends ListPresenter<AlbumListViewHolder> {
		void onHolderClick(int albumId);
		void onAddToPlaylist(int albumId);
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