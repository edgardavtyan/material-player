package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.components.albums.Album;

public interface AlbumListMvp {
	interface Presenter {
		void bindViewHolder(AlbumListViewHolder holder, int position);
		int getItemCount();
		void onItemClicked(int albumId);
		void addToPlaylist(int albumId);
		void onCreate();
		void onDestroy();
	}

	interface Model {
		Album getAlbumAtIndex(int index);
		int getAlbumsCount();
		void addToPlaylist(int albumId);
	}

	interface View {
		void goToAlbumDetail(int albumId);
		void notifyDataChanged();
	}
}
