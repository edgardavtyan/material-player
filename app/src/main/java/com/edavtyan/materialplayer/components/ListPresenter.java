package com.edavtyan.materialplayer.components;

import com.edavtyan.materialplayer.components.album_mvp.AlbumListViewHolder;

public interface ListPresenter<VH> {
	void bindViewHolder(VH holder, int position);
	int getItemCount();
	void onCreate();
	void onDestroy();
}
