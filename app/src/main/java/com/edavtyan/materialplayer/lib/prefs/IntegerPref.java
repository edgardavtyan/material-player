package com.edavtyan.materialplayer.lib.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import lombok.Setter;

public abstract class IntegerPref implements SharedPreferences.OnSharedPreferenceChangeListener {
	private final AdvancedSharedPrefs prefs;
	private final String key;
	private final int defaultValue;
	private int value;

	private @Setter @Nullable OnPrefChanged onPrefChangedListener;

	public interface OnPrefChanged {
		void onPrefChanged(int value);
	}

	protected abstract int getKeyId();
	protected abstract int getDefaultValueId();

	public IntegerPref(Context context, AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);

		key = context.getString(getKeyId());
		defaultValue = context.getResources().getInteger(getDefaultValueId());
		value = prefs.getInt(key, defaultValue);
	}

	public double getValue() {
		return prefs.getInt(key, defaultValue);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (this.key.equals(key)) {
			value = prefs.getInt(key, defaultValue);
			onPrefChanged(value);

			if (onPrefChangedListener != null) {
				onPrefChangedListener.onPrefChanged(value);
			}
		}
	}

	public void onPrefChanged(int value) {
	}
}
