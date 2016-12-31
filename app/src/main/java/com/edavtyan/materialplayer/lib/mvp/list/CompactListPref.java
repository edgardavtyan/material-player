package com.edavtyan.materialplayer.lib.mvp.list;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.BooleanPref;

import lombok.Setter;

public class CompactListPref extends BooleanPref {
	public interface OnChangedListener {
		void onCompactListPrefChanged(boolean value);
	}

	private @Setter OnChangedListener onCompactListPrefChangedListener;

	public CompactListPref(Context context, AdvancedSharedPrefs prefs) {
		super(context, prefs);
	}

	@Override
	protected int getKeyId() {
		return R.string.pref_compactList_key;
	}

	@Override
	protected int getDefaultValueId() {
		return R.bool.pref_compactList_defaultValue;
	}

	@Override
	protected void onPrefChanged(boolean value) {
		if (onCompactListPrefChangedListener != null)
			onCompactListPrefChangedListener.onCompactListPrefChanged(value);
	}
}
