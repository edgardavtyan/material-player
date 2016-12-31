package com.edavtyan.materialplayer.lib.mvp.list;

import android.content.SharedPreferences;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import lombok.Getter;
import lombok.Setter;

public class ListModel implements ListMvp, SharedPreferences.OnSharedPreferenceChangeListener {
	private static final String PREF_COMPACT_LISTS_KEY = "pref_compactList";
	private static final boolean PREF_COMPACT_LISTS_DEFAULT = false;

	private final AdvancedSharedPrefs prefs;

	private @Getter boolean isCompactModeEnabled;

	private @Setter Model.OnCompactModeChangedListener onCompactModeChangedListener;

	public ListModel(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);
		isCompactModeEnabled = prefs.getBoolean(PREF_COMPACT_LISTS_KEY, PREF_COMPACT_LISTS_DEFAULT);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(PREF_COMPACT_LISTS_KEY) && onCompactModeChangedListener != null) {
			isCompactModeEnabled = prefs.getBoolean(PREF_COMPACT_LISTS_KEY, PREF_COMPACT_LISTS_DEFAULT);
			onCompactModeChangedListener.onCompactModeChanged();
		}
	}
}
