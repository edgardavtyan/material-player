package com.edavtyan.materialplayer.lib.replaygain;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import lombok.Setter;

public class ReplayGainPrefs implements SharedPreferences.OnSharedPreferenceChangeListener {
	private final AdvancedSharedPrefs prefs;
	private final String rgEnabledKey;
	private final boolean rgEnabledDefault;
	private final String rgAlbumKey;
	private final boolean rgAlbumDefault;
	private final String rgPreampKey;
	private final int rgPreampDefault;
	private final String rgLimitKey;
	private final boolean rgLimitDefault;

	private @Setter OnPrefChanged onPrefChangedListener;

	public interface OnPrefChanged {
		void onChanged();
	}

	public ReplayGainPrefs(Context context, AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);

		Resources res = context.getResources();
		rgEnabledKey = res.getString(R.string.pref_replaygain_enabled_key);
		rgEnabledDefault = res.getBoolean(R.bool.pref_replaygain_enabled_default);
		rgAlbumKey = res.getString(R.string.pref_replaygain_album_key);
		rgAlbumDefault = res.getBoolean(R.bool.pref_replaygain_album_default);
		rgPreampKey = res.getString(R.string.pref_replaygain_gain_key);
		rgPreampDefault = res.getInteger(R.integer.pref_replaygain_gain_default);
		rgLimitKey = res.getString(R.string.pref_replaygain_limit_key);
		rgLimitDefault = res.getBoolean(R.bool.pref_replaygain_limit_default);
	}

	public boolean isEnabled() {
		return prefs.getBoolean(rgEnabledKey, rgEnabledDefault);
	}

	public boolean isAlbumGainUsed() {
		return prefs.getBoolean(rgAlbumKey, rgAlbumDefault);
	}

	public boolean isLimiterEnabled() {
		return prefs.getBoolean(rgLimitKey, rgLimitDefault);
	}

	public double getPreamp() {
		return (double) prefs.getInt(rgPreampKey, rgPreampDefault) / 10;
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(rgEnabledKey) ||
			key.equals(rgAlbumKey) ||
			key.equals(rgPreampKey) ||
			key.equals(rgLimitKey)) {
			onPrefChangedListener.onChanged();
			return;
		}
	}
}
