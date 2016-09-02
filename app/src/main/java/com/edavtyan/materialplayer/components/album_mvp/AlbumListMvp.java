package com.edavtyan.materialplayer.components.album_mvp;

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
		void update();
		void bindService();
		void unbindService();
	}

	interface View {
		void goToAlbumDetail(int albumId);
	}
}
