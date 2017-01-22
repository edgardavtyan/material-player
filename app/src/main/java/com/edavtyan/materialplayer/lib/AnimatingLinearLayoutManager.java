package com.edavtyan.materialplayer.lib;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class AnimatingLinearLayoutManager extends LinearLayoutManager {
	public AnimatingLinearLayoutManager(Context context) {
		super(context);
	}

	@Override
	public boolean supportsPredictiveItemAnimations() {
		return true;
	}
}
