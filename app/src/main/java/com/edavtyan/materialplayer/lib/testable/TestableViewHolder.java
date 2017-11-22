package com.edavtyan.materialplayer.lib.testable;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TestableViewHolder extends RecyclerView.ViewHolder {
	public TestableViewHolder(View itemView) {
		super(itemView);
	}

	public int getAdapterPositionNonFinal() {
		return super.getAdapterPosition();
	}
}
