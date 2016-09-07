package com.edavtyan.materialplayer.lib.base;

import android.view.View;

import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;

public abstract class BaseViewHolder extends TestableViewHolder {
	private final View itemView;

	public BaseViewHolder(View itemView) {
		super(itemView);
		this.itemView = itemView;
	}

	@SuppressWarnings("unchecked")
	public <T> T findView(int resId) {
		return (T) itemView.findViewById(resId);
	}
}
