package com.edavtyan.materialplayer.lib.mvp.list;

public abstract class ListPresenter<VH>
		implements ListMvp.Presenter<VH>,
				   ListMvp.Model.OnCompactModeChangedListener {

	private final ListMvp.Model model;
	private final ListMvp.View view;

	public ListPresenter(ListMvp.Model model, ListMvp.View view) {
		this.model = model;
		this.model.setOnCompactModeChangedListener(this);
		this.view = view;
	}

	@Override public void onUpdateCompactMode() {

	}

	@Override
	public boolean isCompactModeEnabled() {
		return model.isCompactModeEnabled();
	}

	@Override public void onCompactModeChanged() {
		view.notifyDataSetChanged();
	}
}
