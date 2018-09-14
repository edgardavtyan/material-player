package com.edavtyan.materialplayer.lib.replaygain;

import android.content.Context;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.IntegerPref;

import lombok.Setter;

public class ReplayGainPreampPref extends IntegerPref {
	private @Setter @Nullable OnPreampChanged onPreampChangedListener;

	public interface OnPreampChanged {
		void onPreampChanged(double preamp);
	}

	public ReplayGainPreampPref(Context context, AdvancedSharedPrefs prefs) {
		super(context, prefs);
	}

	@Override
	protected int getKeyId() {
		return R.string.pref_replaygain_gain_key;
	}

	@Override
	protected int getDefaultValueId() {
		return R.integer.pref_replaygain_gain_default;
	}

	public double getPreamp() {
		return getValue() / 10;
	}

	@Override
	public void onPrefChanged(int value) {
		if (onPreampChangedListener != null) {
			onPreampChangedListener.onPreampChanged(getPreamp());
		}
	}
}
