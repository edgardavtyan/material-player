package com.edavtyan.materialplayer.app.music.effects;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.h6ah4i.android.media.opensl.audiofx.OpenSLPreAmp;

public class Amplifier implements StrengthBasedEffect {
    private static final String PREF_ENABLED = "pref_amplifier_enabled";
    private static final String PREF_STRENGTH = "pref_amplifier_strength";
    private static final int MAX_STRENGTH = 1000;


    private final OpenSLPreAmp amplifier;
    private final SharedPreferences prefs;


    public Amplifier(Context context, OpenSLPreAmp amplifier) {
        this.amplifier = amplifier;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        amplifier.setEnabled(true);
        prefs.edit().putBoolean(PREF_ENABLED, isEnabled).apply();
    }

    @Override
    public int getMaxStrength() {
        return MAX_STRENGTH;
    }

    @Override
    public int getStrength() {
        return (int) amplifier.getLevel();
    }

    @Override
    public void saveSettings() {
        prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
    }

    @Override
    public void setStrength(int strength) {
        amplifier.setLevel(strength);
    }
}
