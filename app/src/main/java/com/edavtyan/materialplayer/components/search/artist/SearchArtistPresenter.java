package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.search.base.SearchPresenter;

public class SearchArtistPresenter extends SearchPresenter<SearchArtistViewHolder> {
	private final SearchArtistModel model;
	private final SearchArtistFragment view;

	public SearchArtistPresenter(SearchArtistModel model, SearchArtistFragment view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		model.update(query);
		view.updateArtists();
	}

	@Override
	public void onBindViewHolder(SearchArtistViewHolder holder, int position) {
		holder.setTitle(model.getItemAt(position).getTitle());
	}
}
