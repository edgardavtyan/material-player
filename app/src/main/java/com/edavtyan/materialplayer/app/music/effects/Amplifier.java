package com.edavtyan.materialplayer.app.music.effects;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.h6ah4i.android.media.audiofx.IPreAmp;

public class Amplifier implements StrengthBasedEffect {
    private static final String PREF_ENABLED = "pref_amplifier_enabled";
    private static final String PREF_STRENGTH = "pref_amplifier_strength";
    private static final int MAX_STRENGTH = 100;
    private static final int DEFAULT_STRENGTH = 50;


    private final IPreAmp amplifier;
    private final SharedPreferences prefs;


    public Amplifier(Context context, IPreAmp amplifier) {
        this.amplifier = amplifier;
        amplifier.setEnabled(true);

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
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
        int strength = (int) ((amplifier.getLevel() - 1) * MAX_STRENGTH);
        Log.i("Amplifier", "getStrength() = " + strength);
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        float strengthFloat = (strength / (float) MAX_STRENGTH) + 1;
        if (strengthFloat > 2.0f) strengthFloat = 2.0f;
        Log.i("Amplifier", "setStrength() = " + strengthFloat);
        amplifier.setLevel(strengthFloat);
    }

    @Override
    public void saveSettings() {
        prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
    }
}
