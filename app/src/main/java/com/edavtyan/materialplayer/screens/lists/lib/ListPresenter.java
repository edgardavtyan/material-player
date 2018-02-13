package com.edavtyan.materialplayer.screens.lists.lib;

public abstract class ListPresenter<VH> {
	public abstract void onBindViewHolder(VH holder, int position);
	public abstract int getItemCount();
	public abstract void onDestroy();
	public abstract void onCreate();
}
