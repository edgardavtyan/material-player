package com.edavtyan.custompreference.simple_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.custompreference.R;

public class SimpleListAdapter
		extends RecyclerView.Adapter<SimpleListViewHolder>
		implements SimpleListViewHolder.OnHolderClickListener {

	private final Context context;
	private final SimpleListPresenter presenter;

	public SimpleListAdapter(Context context, SimpleListPresenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public SimpleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_simple, parent, false);
		return new SimpleListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(SimpleListViewHolder holder, int position) {
		presenter.bindViewHolder(holder, position);
		holder.setOnHolderClickListener(this);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public void onHolderClick(CharSequence value) {
		presenter.onListItemSelected(value);
		notifyDataSetChanged();
	}

}
