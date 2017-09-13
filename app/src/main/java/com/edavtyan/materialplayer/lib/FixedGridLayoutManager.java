package com.edavtyan.materialplayer.lib;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

public class FixedGridLayoutManager extends GridLayoutManager {
	public FixedGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public FixedGridLayoutManager(Context context, int spanCount) {
		super(context, spanCount);
	}

	public FixedGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
		super(context, spanCount, orientation, reverseLayout);
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
