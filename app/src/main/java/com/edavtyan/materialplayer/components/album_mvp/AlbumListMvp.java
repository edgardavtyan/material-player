package com.edavtyan.materialplayer.components.album_mvp;

public interface AlbumListMvp {
	interface Presenter {
		void bindViewHolder(AlbumListViewHolder holder, int position);
		int getItemCount();
		void onItemClicked(int albumId);
		void addToPlaylist(int albumId);
	}
}
