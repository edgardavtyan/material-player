package com.edavtyan.materialplayer.ui.lists.lib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public abstract class ListAdapter<VH extends RecyclerView.ViewHolder>
		extends TestableRecyclerAdapter<VH> {

	private final Context context;
	private final ListPresenter<VH> presenter;

	public ListAdapter(Context context, ListPresenter<VH> presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	public abstract int getLayoutId();
	public abstract VH onCreateViewHolder(Context context, View view);

	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (inflater == null) {
			throw new IllegalStateException("LayoutInflater service was not found");
		}

		View view = inflater.inflate(viewType, parent, false);
		return onCreateViewHolder(context, view);
	}

	@Override
	public void onBindViewHolder(VH holder, int position) {
		presenter.onBindViewHolder(holder, position);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public int getItemViewType(int position) {
		return getLayoutId();
	}
}
