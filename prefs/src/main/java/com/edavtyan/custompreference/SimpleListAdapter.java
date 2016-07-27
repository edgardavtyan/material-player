package com.edavtyan.custompreference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SimpleListAdapter extends ListAdapter<SimpleListController, SimpleListViewHolder>
		implements SimpleListViewHolder.OnHolderClickListener {

	public SimpleListAdapter(Context context, SimpleListController controller) {
		super(context, controller);
	}


	@Override
	public SimpleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_radio, parent, false);
		return new SimpleListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(SimpleListViewHolder holder, int position) {
		holder.setTitle(controller.getEntries().get(position).toString());
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
