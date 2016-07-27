package com.edavtyan.custompreference;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public abstract class ListAdapter<TController extends ListController, THolder extends RecyclerView.ViewHolder>
		extends RecyclerView.Adapter<THolder> {

	protected final Context context;
	protected final TController controller;


	public ListAdapter(Context context, TController controller) {
		this.context = context;
		this.controller = controller;
	}


	@Override
	public int getItemCount() {
		return controller.getEntries().size();
	}
}
