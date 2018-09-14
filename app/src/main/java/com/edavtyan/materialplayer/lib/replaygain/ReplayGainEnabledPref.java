package com.edavtyan.materialplayer.lib.replaygain;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.BooleanPref;

public class ReplayGainEnabledPref extends BooleanPref {
	public ReplayGainEnabledPref(Context context, AdvancedSharedPrefs prefs) {
		super(context, prefs);
	}

	@Override
	protected int getKeyId() {
		return R.string.pref_replaygain_enabled_key;
	}

	@Override
	protected int getDefaultValueId() {
		return R.bool.pref_replaygain_enabled_default;
	}
}
