package com.edavtyan.materialplayer.player.effects.equalizer;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;

public class StandardPresetsPrefs extends PresetsPrefs {
	public StandardPresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		super(prefs, "pref_std_index", "pref_std_custom", "pref_std_type");
	}
}
