package com.edavtyan.materialplayer.ui.now_playing;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class LyricsVisiblePref {
	private static final String PREF_KEY = "pref_lyrics_visible";
	private static final boolean PREF_DEFAULT = false;

	private final AdvancedSharedPrefs prefs;

	public LyricsVisiblePref(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public boolean isVisible() {
		return prefs.getBoolean(PREF_KEY, PREF_DEFAULT);
	}

	public void setVisible(boolean isVisible) {
		prefs.edit().putBoolean(PREF_KEY, isVisible).apply();
	}

	public void toggleVisible() {
		prefs.edit().putBoolean(PREF_KEY, !isVisible()).apply();
	}
}
