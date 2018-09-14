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

	private @Setter OnPreampChanged onPreampChangedListener;
	private @Setter OnEnabledChanged onEnabledChangedListener;
	private @Setter OnAlbumUsedChanged onAlbumUsedChangedListener;

	public interface OnPreampChanged {
		void onPreampChanged(double preamp);
	}

	public interface OnEnabledChanged {
		void onEnabledChanged(boolean enabled);
	}

	public interface OnAlbumUsedChanged {
		void onAlbumUsedChanged(boolean albumGainUsed);
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
	}

	public boolean getEnabled() {
		return prefs.getBoolean(rgEnabledKey, rgEnabledDefault);
	}

	public boolean isAlbumGainUsed() {
		return prefs.getBoolean(rgAlbumKey, rgAlbumDefault);
	}

	public int getPreamp() {
		return prefs.getInt(rgPreampKey, rgPreampDefault);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(rgEnabledKey)) {
			onEnabledChangedListener.onEnabledChanged(getEnabled());
			return;
		}

		if (key.equals(rgAlbumKey)) {
			onAlbumUsedChangedListener.onAlbumUsedChanged(isAlbumGainUsed());
			return;
		}

		if (key.equals(rgPreampKey)) {
			onPreampChangedListener.onPreampChanged(getPreamp());
			return;
		}
	}
}
