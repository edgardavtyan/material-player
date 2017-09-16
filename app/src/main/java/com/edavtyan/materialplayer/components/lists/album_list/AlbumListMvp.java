package com.edavtyan.materialplayer.components.lists.album_list;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.components.lists.lib.ListMvp;

@SuppressWarnings("unused")
public interface AlbumListMvp {
	interface Presenter extends ListMvp.Presenter<AlbumListViewHolder> {
		void onHolderClick(int position);
		void onAddToPlaylist(int position);
	}

	interface Model extends ListMvp.Model {
		Album getAlbumAtIndex(int index);
		int getAlbumsCount();
		void addToPlaylist(int albumId);
		void update();
		void bindService();
		void unbindService();
	}

	interface View extends ListMvp.View {
		void gotoAlbumDetail(int albumId);
	}
}
