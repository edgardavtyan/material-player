package com.example.custompreference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DescriptionListAdapter
		extends ListAdapter<DescriptionListController, DescriptionListViewHolder>
		implements DescriptionListViewHolder.OnHolderClickListener {

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
		holder.setValue(controller.getValues().get(position));
		holder.setOnHolderClickListener(this);
	}

	@Override
	public void onHolderClick(CharSequence value) {
		controller.savePref(value);
		controller.closeDialog();
		notifyDataSetChanged();
	}
}
