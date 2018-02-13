package com.edavtyan.materialplayer.ui.lists.lib;

public interface ListPresenter<VH> {
	void onBindViewHolder(VH holder, int position);
	int getItemCount();
	void onDestroy();
	void onCreate();
}
