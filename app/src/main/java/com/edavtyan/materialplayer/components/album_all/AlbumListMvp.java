package com.edavtyan.materialplayer.components.album_all;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.lib.mvp.list.ListMvp;
import com.edavtyan.materialplayer.lib.mvp.list.ListPresenter;

@SuppressWarnings("unused")
public interface AlbumListMvp {
	interface Presenter extends ListPresenter<AlbumListViewHolder> {
		void onHolderClick(int albumId);
		void onAddToPlaylist(int albumId);
		boolean isCompactModeEnabled();
	}

	interface Model extends ListMvp.Model {
		Album getAlbumAtIndex(int index);
		int getAlbumsCount();
		void addToPlaylist(int albumId);
		void update();
		void bindService();
		void unbindService();
	}

	interface View {
		void goToAlbumDetail(int albumId);
		void notifyDataSetChanged();
	}
}
