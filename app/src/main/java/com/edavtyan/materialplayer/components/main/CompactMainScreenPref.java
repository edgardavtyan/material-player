package com.edavtyan.materialplayer.components.main;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.BooleanPref;

public class CompactMainScreenPref extends BooleanPref {
	public CompactMainScreenPref(Context context, AdvancedSharedPrefs prefs) {
		super(context, prefs);
	}

	@Override
	protected int getKeyId() {
		return R.string.pref_compact_main_key;
	}

	@Override
	protected int getDefaultValueId() {
		return R.bool.pref_compact_main_default;
	}
}
