package com.edavtyan.materialplayer.components.search;

import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.components.search.base.SearchPresenter;

public class SearchQueryInit implements SearchActivity.OnSearchQueryChangedListener {
	private final SearchActivity activity;
	private final SearchPresenter presenter;

	public SearchQueryInit(Fragment fragment, SearchPresenter presenter) {
		activity = (SearchActivity) fragment.getActivity();
		this.presenter = presenter;
	}

	public void init() {
		activity.addOnSearchQueryChangedListener(this);
	}

	public void destroy() {
		activity.removeOnSearchQueryChangedListener(this);
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}
}
