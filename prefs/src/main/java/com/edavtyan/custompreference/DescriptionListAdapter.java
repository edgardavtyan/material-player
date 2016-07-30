package com.edavtyan.custompreference;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lombok.Setter;

public class DescriptionListAdapter
		extends RecyclerView.Adapter<DescriptionListViewHolder>
		implements SimpleListViewHolder.OnHolderClickListener {

	private final Context context;
	private final DescriptionListController model;
	private @Setter OnHolderClickListener onHolderClickListener;


	interface OnHolderClickListener {
		void onHolderClick(CharSequence value);
	}


	public DescriptionListAdapter(Context context, DescriptionListController model) {
		this.context = context;
		this.model = model;
	}


	@Override
	public DescriptionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_description, parent, false);
		return new DescriptionListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(DescriptionListViewHolder holder, int position) {
		holder.setTitle(model.getEntries().get(position));
		holder.setDescription(model.getSummaries().get(position));
		holder.setChecked(model.getPrefSelectedAtIndex(position));
		holder.setValue(model.getValues().get(position));
		holder.setOnHolderClickListener(this);
	}

	@Override
	public int getItemCount() {
		return model.getEntries().size();
	}


	@Override
	public void onHolderClick(CharSequence value) {
		if (onHolderClickListener != null) {
			onHolderClickListener.onHolderClick(value);
		}
	}
}
