package com.edavtyan.materialplayer.lib.mvp.list;

import lombok.Getter;
import lombok.Setter;

public class ListModel implements ListMvp, CompactListPref.OnChangedListener {

	// This keeps strong reference to OnCompactModeChangedListener. Without this field
	// the listener gets garbage immediately after constructor finishes and stops working
	@SuppressWarnings("FieldCanBeLocal")
	private final CompactListPref compactListPref;

	private @Getter boolean isCompactModeEnabled;

	private @Setter Model.OnCompactModeChangedListener onCompactModeChangedListener;

	public ListModel(CompactListPref compactListPref) {
		this.compactListPref = compactListPref;
		this.compactListPref.setOnCompactListPrefChangedListener(this);
		isCompactModeEnabled = this.compactListPref.getValue();
	}

	@Override public void onCompactListPrefChanged(boolean value) {
		if (onCompactModeChangedListener != null) {
			isCompactModeEnabled = value;
			onCompactModeChangedListener.onCompactModeChanged();
		}
	}
}
