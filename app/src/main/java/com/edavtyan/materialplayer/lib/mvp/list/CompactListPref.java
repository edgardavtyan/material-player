package com.edavtyan.materialplayer.lib.mvp.list;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.BooleanPref;

public class CompactListPref extends BooleanPref {
	public CompactListPref(Context context, AdvancedSharedPrefs prefs) {
		super(context, prefs);
	}

	@Override
	protected int getKeyId() {
		return R.string.pref_compact_list_key;
	}

	@Override
	protected int getDefaultValueId() {
		return R.bool.pref_compact_list_default;
	}
}
