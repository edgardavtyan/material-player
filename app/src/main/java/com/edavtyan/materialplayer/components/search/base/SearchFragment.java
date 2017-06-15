package com.edavtyan.materialplayer.components.search.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.SearchActivity;
import com.edavtyan.materialplayer.lib.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class SearchFragment
		extends BaseFragment
		implements SearchActivity.OnSearchQueryChangedListener {

	@BindView(R.id.list) RecyclerView list;

	private SearchActivity activity;

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);
		ButterKnife.bind(this, view);
		list.setLayoutManager(new LinearLayoutManager(getContext()));
		onPostCreateView();
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

	protected void setAdapter(RecyclerView.Adapter adapter) {
		list.setAdapter(adapter);
	}

	protected abstract void onPostCreateView();
}
