package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.components.search.base.SearchPresenter;

public class SearchAlbumPresenter extends SearchPresenter<SearchAlbumViewHolder> {
	private final SearchAlbumModel model;
	private final SearchAlbumFragment view;

	public SearchAlbumPresenter(SearchAlbumModel model, SearchAlbumFragment view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		model.update(query);
		view.updateData();
	}

	@Override
	public void onBindViewHolder(SearchAlbumViewHolder holder, int position) {
		holder.setTitle(model.getItemAt(position).getTitle());
	}
}
