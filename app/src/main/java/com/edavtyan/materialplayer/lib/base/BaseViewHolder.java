package com.edavtyan.materialplayer.lib.base;

import android.view.View;

import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;

import butterknife.ButterKnife;

public abstract class BaseViewHolder extends TestableViewHolder {
	public BaseViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
