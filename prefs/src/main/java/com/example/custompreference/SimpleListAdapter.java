package com.example.custompreference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lombok.Setter;

public class SimpleListAdapter extends ListAdapter<SimpleListController, SimpleListViewHolder>
		implements SimpleListViewHolder.OnHolderClickListener {

	private @Setter OnItemSelectedListener onItemSelectedListener;


	interface OnItemSelectedListener {
		void onItemSelectedListener(CharSequence value);
	}


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
		holder.setTitleView(controller.getEntries().get(position).toString());
		holder.setChecked(controller.getPrefSelectedAtIndex(position));
		holder.setValue(controller.getValues().get(position));
		holder.setOnHolderClickListener(this);
	}

	@Override
	public void onHolderClick(CharSequence value) {
		controller.savePref(value);
		notifyDataSetChanged();
		if (onItemSelectedListener != null) {
			onItemSelectedListener.onItemSelectedListener(value);
		}
	}
}
