package com.edavtyan.materialplayer.components.lists.lib;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class ListFactory extends BaseFactory {
	private CompactListPref compactListPref;

	public ListFactory(Context context) {
		super(context);
	}

	public CompactListPref getCompactListPref() {
		if (compactListPref == null)
			compactListPref = new CompactListPref(getContext(), getPrefs());
		return compactListPref;
	}
}
