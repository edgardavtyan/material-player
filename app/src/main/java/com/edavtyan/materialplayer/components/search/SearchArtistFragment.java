package com.edavtyan.materialplayer.components.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchArtistFragment extends BaseFragment
		implements SearchActivity.OnSearchQueryChangedListener {
	@BindView(R.id.list) RecyclerView list;

	private SearchAdapter adapter;
	private SearchActivity activity;
	private SearchArtistPresenter presenter;

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);

		ButterKnife.bind(this, view);

		SearchArtistFactory factory = app.getSearchFactory(getContext(), this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();

		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(getContext()));

		return view;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		activity = (SearchActivity) context;
		activity.addOnSearchQueryChangedListener(this);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		activity.removeOnSearchQueryChangedListener(this);
	}

	public void updateArtists(List<Artist> artists) {
		adapter.updateSearchResults(artists);
	}

	@Override
	public void onSearchQueryChanged(String query) {
		presenter.onSearchChange(query);
	}
}
