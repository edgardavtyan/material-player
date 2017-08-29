package com.edavtyan.materialplayer.lib.mvp.list;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseFragment;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.utils.WindowUtils;

public abstract class ListFragment<TPresenter extends ListMvp.Presenter>
		extends BaseFragment
		implements ListMvp.View {

	protected TestableRecyclerAdapter adapter;
	protected TPresenter presenter;

	protected void initListView(TPresenter presenter, TestableRecyclerAdapter adapter) {
		this.presenter = presenter;
		this.adapter = adapter;
		presenter.onCreate();
	}

	@LayoutRes
	protected int getLayoutId() {
		return R.layout.fragment_list;
	}

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);

		RecyclerView list = findView(view, R.id.list);
		list.setAdapter(adapter);

		int spanCount = WindowUtils.isPortrait(getContext()) ? 1 : 2;
		list.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.onUpdateCompactMode();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
