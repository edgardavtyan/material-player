package com.edavtyan.materialplayer.components.search.base;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewImpl implements SearchActivity.OnSearchQueryChangedListener {
	@BindView(R.id.search_empty) TextView searchEmptyView;
	@BindView(R.id.search_not_found) TextView searchNotFoundView;
	@BindView(R.id.list) RecyclerView list;

	private final SearchActivity activity;
	private final SearchPresenter presenter;
	private final Fragment fragment;

	public SearchViewImpl(Fragment fragment, SearchPresenter presenter) {
		this.fragment = fragment;
		this.presenter = presenter;
		activity = (SearchActivity) fragment.getActivity();
	}

	public void init() {
		ButterKnife.bind(this, fragment.getView());
		activity.addOnSearchQueryChangedListener(this);
		presenter.onSearchChange(activity.getSearchQuery());
	}

	public void destroy() {
		activity.removeOnSearchQueryChangedListener(this);
	}

	public void showEmptyQuery() {
		list.setVisibility(View.INVISIBLE);
		searchEmptyView.setVisibility(View.VISIBLE);
		searchNotFoundView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onSearchQueryChanged(String query) {
		list.setVisibility(View.VISIBLE);
		searchEmptyView.setVisibility(View.INVISIBLE);
		searchNotFoundView.setVisibility(View.INVISIBLE);
		presenter.onSearchChange(query);
	}

	public void showEmptyResult() {
		list.setVisibility(View.INVISIBLE);
		searchEmptyView.setVisibility(View.INVISIBLE);
		searchNotFoundView.setVisibility(View.VISIBLE);
	}
}
