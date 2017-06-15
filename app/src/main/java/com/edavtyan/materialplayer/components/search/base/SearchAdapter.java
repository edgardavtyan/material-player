package com.edavtyan.materialplayer.components.search.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.lib.base.BaseViewHolder;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public abstract class SearchAdapter<VH extends BaseViewHolder>
		extends TestableRecyclerAdapter<VH> {
	private final Context context;
	private final SearchPresenter<VH> presenter;

	public SearchAdapter(Context context, SearchPresenter<VH> presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View itemView = inflater.inflate(getLayoutId(), parent, false);
		return onCreateViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(VH holder, int position) {
		presenter.onBindViewHolder(holder, position);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	public void updateData() {
		notifyDataSetChanged();
	}

	@LayoutRes
	protected abstract int getLayoutId();

	protected abstract VH onCreateViewHolder(View itemView);
}
