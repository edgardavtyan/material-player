package com.edavtyan.materialplayer.screens.lists.lib;

public abstract class ListPresenter<VH> {

	private final ListModel model;
	private final ListView view;
	private boolean isCompactModeEnabled;

	public ListPresenter(ListModel model, ListView view) {
		this.model = model;
		this.view = view;
	}

	public abstract void onBindViewHolder(VH holder, int position);
	public abstract int getItemCount();
	public abstract void onDestroy();

	public void onCreate() {
		isCompactModeEnabled = model.isCompactModeEnabled();
	}

	public boolean isCompactModeEnabled() {
		return model.isCompactModeEnabled();
	}

	public void onUpdateCompactMode() {
		if (isCompactModeEnabled != isCompactModeEnabled()) {
			isCompactModeEnabled = isCompactModeEnabled();
			view.notifyDataSetChanged();
		}
	}

}
