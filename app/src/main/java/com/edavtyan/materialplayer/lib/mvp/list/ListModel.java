package com.edavtyan.materialplayer.lib.mvp.list;

public class ListModel implements ListMvp.Model {
	private final CompactListPref compactListPref;

	public ListModel(CompactListPref compactListPref) {
		this.compactListPref = compactListPref;
	}

	@Override
	public boolean isCompactModeEnabled() {
		return compactListPref.getValue();
	}
}
