package com.edavtyan.utils.advanced;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class GenericViewHolder extends RecyclerView.ViewHolder {
	private final View itemView;

	public GenericViewHolder(View itemView) {
		super(itemView);
		this.itemView = itemView;
	}

	@SuppressWarnings("unchecked")
	public <T> T findView(@IdRes int id) {
		return (T) itemView.findViewById(id);
	}
}
