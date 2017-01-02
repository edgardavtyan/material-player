package com.edavtyan.materialplayer.lib.testable;

import android.view.View;

import com.edavtyan.utils.advanced.GenericViewHolder;

public class TestableViewHolder extends GenericViewHolder {
	public TestableViewHolder(View itemView) {
		super(itemView);
	}

	public int getAdapterPositionNonFinal() {
		return super.getAdapterPosition();
	}
}
