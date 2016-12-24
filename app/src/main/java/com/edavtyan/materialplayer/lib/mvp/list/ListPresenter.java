package com.edavtyan.materialplayer.lib.mvp.list;

public interface ListPresenter<VH> {
	void onBindViewHolder(VH holder, int position);
	int getItemCount();
	void onCreate();
	void onDestroy();
}
