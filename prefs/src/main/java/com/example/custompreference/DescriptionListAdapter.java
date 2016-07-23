package com.example.custompreference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lombok.Setter;

public class DescriptionListAdapter
		extends ListAdapter<DescriptionListController, DescriptionListViewHolder>
		implements DescriptionListViewHolder.OnHolderClickListener {

	private @Setter OnItemSelectedListener onItemSelectedListener;


	interface OnItemSelectedListener {
		void onItemSelectedListener();
	}


	public DescriptionListAdapter(Context context, DescriptionListController controller) {
		super(context, controller);
	}


	@Override
	public DescriptionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_description, parent, false);
		return new DescriptionListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(DescriptionListViewHolder holder, int position) {
		holder.setTitle(controller.getEntries().get(position));
		holder.setDescription(controller.getSummaries().get(position));
		holder.setChecked(controller.getPrefSelectedAtIndex(position));
		holder.setOnHolderClickListener(this);
	}

	@Override
	public void onHolderClick(int position) {
		if (onItemSelectedListener != null) {
			onItemSelectedListener.onItemSelectedListener();
		}
	}
}
