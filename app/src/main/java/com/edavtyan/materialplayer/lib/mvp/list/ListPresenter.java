package com.edavtyan.materialplayer.lib.mvp.list;

public abstract class ListPresenter<VH> implements ListMvp.Presenter<VH> {

	private final ListMvp.Model model;
	private final ListMvp.View view;
	private boolean isCompactModeEnabled;

	public ListPresenter(ListMvp.Model model, ListMvp.View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void onCreate() {
		isCompactModeEnabled = model.isCompactModeEnabled();
	}

	@Override
	public boolean isCompactModeEnabled() {
		return model.isCompactModeEnabled();
	}

	@Override
	public void onUpdateCompactMode() {
		if (isCompactModeEnabled != isCompactModeEnabled()) {
			isCompactModeEnabled = isCompactModeEnabled();
			view.notifyDataSetChanged();
		}
	}
}
