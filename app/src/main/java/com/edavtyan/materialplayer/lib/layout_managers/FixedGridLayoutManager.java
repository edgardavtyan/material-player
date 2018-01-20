package com.edavtyan.materialplayer.lib.layout_managers;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

public class FixedGridLayoutManager extends GridLayoutManager {
	public FixedGridLayoutManager(Context context, int spanCount) {
		super(context, spanCount);
	}

	/**
	 * Disable predictive animations. There is a bug in RecyclerView which causes views that
	 * are being reloaded to pull invalid ViewHolders from the internal recycler stack if the
	 * adapter size has decreased since the ViewHolder was recycled.
	 */
	@Override
	public boolean supportsPredictiveItemAnimations() {
		return false;
	}
}
