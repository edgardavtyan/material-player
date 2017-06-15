package com.edavtyan.materialplayer.components.search.base;

public abstract class SearchPresenter<VH> {
	private final SearchModel model;
	private final SearchFragment view;

	public SearchPresenter(SearchModel model, SearchFragment view) {
		this.model = model;
		this.view = view;
	}

	public int getItemCount() {
		return model.getItemCount();
	}

	public abstract void onBindViewHolder(VH holder, int position);
}
