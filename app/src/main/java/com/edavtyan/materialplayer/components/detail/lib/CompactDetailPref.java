package com.edavtyan.materialplayer.components.detail.lib;

import android.content.Context;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.BooleanPref;

public class CompactDetailPref extends BooleanPref {
	public CompactDetailPref(Context context) {
		super(context, new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context)));
	}

	@Override
	protected int getKeyId() {
		return R.string.pref_compact_detail_key;
	}

	@Override
	protected int getDefaultValueId() {
		return R.bool.pref_compact_detail_default;
	}
}
