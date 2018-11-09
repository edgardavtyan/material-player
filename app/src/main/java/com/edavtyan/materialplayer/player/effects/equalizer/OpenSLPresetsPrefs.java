package com.edavtyan.materialplayer.player.effects.equalizer;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;

public class OpenSLPresetsPrefs extends PresetsPrefs {
	public OpenSLPresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		super(prefs, "pref_opensl_index", "pref_opensl_custom", "pref_opensl_type");
	}
}
