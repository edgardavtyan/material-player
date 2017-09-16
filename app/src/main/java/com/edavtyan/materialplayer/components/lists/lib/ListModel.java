package com.edavtyan.materialplayer.components.lists.lib;

import android.content.Context;

import com.edavtyan.materialplayer.lib.mvp.service.ServiceModel;

public class ListModel extends ServiceModel implements ListMvp.Model {
	private final CompactListPref compactListPref;

	public ListModel(Context context, CompactListPref compactListPref) {
		super(context);
		this.compactListPref = compactListPref;
	}

	@Override
	public boolean isCompactModeEnabled() {
		return compactListPref.isEnabled();
	}
}
