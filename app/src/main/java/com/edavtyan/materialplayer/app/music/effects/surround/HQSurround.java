package com.edavtyan.materialplayer.app.music.effects.surround;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.h6ah4i.android.media.audiofx.IVirtualizer;

public class HQSurround implements Surround {
    private static final String PREF_ENABLED = "pref_surround_enabled";
    private static final String PREF_STRENGTH = "pref_surround_strength";
    private static final int MAX_STRENGTH = 1000;


    private final IVirtualizer surround;
    private final SharedPreferences prefs;


    public HQSurround(Context context, IVirtualizer surround) {
        this.surround = surround;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        setEnabled(prefs.getBoolean(PREF_ENABLED, false));
        setStrength(prefs.getInt(PREF_STRENGTH, 0));
    }


    @Override
    public void setEnabled(boolean isEnabled) {
        surround.setEnabled(isEnabled);
        prefs.edit().putBoolean(PREF_ENABLED, isEnabled).apply();
    }

    @Override
    public boolean isEnabled() {
        return surround.getEnabled();
    }

    @Override
    public int getMaxStrength() {
        return MAX_STRENGTH;
    }

    @Override
    public int getStrength() {
        return surround.getRoundedStrength();
    }

    @Override
    public void setStrength(int strength) {
        surround.setStrength((short)strength);
    }

    @Override
    public void saveSettings() {
        prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
    }
}
