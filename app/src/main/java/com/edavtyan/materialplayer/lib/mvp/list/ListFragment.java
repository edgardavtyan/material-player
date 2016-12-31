package com.edavtyan.materialplayer.lib.mvp.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseFragment;

public abstract class ListFragment<TPresenter extends ListMvp.Presenter> extends BaseFragment {
	protected RecyclerView.Adapter adapter;
	protected TPresenter presenter;

	protected void initListView(TPresenter presenter, RecyclerView.Adapter adapter) {
		this.presenter = presenter;
		this.adapter = adapter;
		presenter.onCreate();
	}

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);

		RecyclerView list = findView(view, R.id.list);
		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(getContext()));

		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}
}
