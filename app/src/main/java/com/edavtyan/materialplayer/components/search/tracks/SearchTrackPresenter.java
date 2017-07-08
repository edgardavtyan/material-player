package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.components.search.base.SearchPresenter;
import com.edavtyan.materialplayer.components.track_all.TrackListPresenter;

public class SearchTrackPresenter extends TrackListPresenter implements SearchPresenter {
	private final SearchTrackModel model;
	private final SearchTrackFragment view;

	public SearchTrackPresenter(SearchTrackModel model, SearchTrackFragment view) {
		super(view, model);
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		model.setTrackTitle(query);
		model.update();
		view.notifyDataSetChanged();
	}
}
