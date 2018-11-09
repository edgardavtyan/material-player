package com.edavtyan.materialplayer.ui.search.tracks;

import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListPresenter;
import com.edavtyan.materialplayer.ui.search.base.SearchPresenter;
import com.edavtyan.materialplayer.ui.search.base.SearchPresenterImpl;

public class SearchTrackPresenter extends TrackListPresenter implements SearchPresenter {
	private final SearchPresenterImpl searchPresenterImpl;

	public SearchTrackPresenter(SearchTrackModel model, SearchTrackFragment view, ThemeColors theme) {
		super(view, model, theme);
		this.searchPresenterImpl = new SearchPresenterImpl(model, view);
	}

	public void onSearchChange(String query) {
		searchPresenterImpl.onSearchChange(query);
	}
}
