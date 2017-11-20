package com.edavtyan.materialplayer.components.lists.lib;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.FixedGridLayoutManager;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.modular.fragment.FragmentModule;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragmentModule extends FragmentModule {
	@BindView(R.id.list) RecyclerView list;
	private final Fragment fragment;
	private final TestableRecyclerAdapter adapter;
	private final ListMvp.Presenter presenter;

	public ListFragmentModule(
			Fragment fragment,
			TestableRecyclerAdapter adapter,
			ListMvp.Presenter presenter) {
		this.fragment = fragment;
		this.adapter = adapter;
		this.presenter = presenter;
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void onCreateView(View view) {
		super.onCreateView(view);
		ButterKnife.bind(this, view);

		list.setAdapter(adapter);

		int spanCount = WindowUtils.isPortrait(fragment.getContext()) ? 1 : 2;
		list.setLayoutManager(new FixedGridLayoutManager(fragment.getContext(), spanCount));

		presenter.onCreate();
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
}
