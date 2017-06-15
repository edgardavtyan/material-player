package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.components.search.base.SearchPresenter;

public class SearchTrackPresenter extends SearchPresenter<SearchTrackViewHolder> {
	private final SearchTrackModel model;
	private final SearchTrackFragment view;

	public SearchTrackPresenter(SearchTrackModel model, SearchTrackFragment view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		model.update(query);
		view.updateTracks();
	}

	@Override
	public void onBindViewHolder(SearchTrackViewHolder holder, int position) {
		holder.setTitle(model.getItemAt(position).getTitle());
	}
}
