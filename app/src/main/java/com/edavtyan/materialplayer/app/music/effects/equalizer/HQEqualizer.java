package com.edavtyan.materialplayer.app.music.effects.equalizer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.h6ah4i.android.media.audiofx.IEqualizer;

import java.util.ArrayList;
import java.util.List;

public class HQEqualizer implements Equalizer {
    private final IEqualizer equalizer;
    private final Gson gson;
    private final SharedPreferences prefs;
    private final List<EqualizerPreset> presets;
    private final int[] frequencies;
    private EqualizerPreset currentPreset;


    public HQEqualizer(Context context, IEqualizer equalizer) {
        this.equalizer = equalizer;
        this.equalizer.setEnabled(true);

        frequencies = new int[equalizer.getNumberOfBands()];
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = equalizer.getCenterFreq((short)i) / 1000;
        }
        
        gson = new Gson();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        presets = new ArrayList<>();

        EqualizerPreset preset = new EqualizerPreset();
        preset.setIndex(0);
        preset.setName("Flat");
        preset.setGains(new int[getBandsCount()]);
        presets.add(preset);

        currentPreset = gson.fromJson(
                prefs.getString("pref_current_preset", null),
                EqualizerPreset.class);
        if (currentPreset == null) currentPreset = presets.get(0);

        usePreset(currentPreset);
    }

    @Override
    public int getBandsCount() {
        return equalizer.getNumberOfBands();
    }

    @Override
    public int[] getFrequencies() {
        return frequencies;
    }

    @Override
    public int[] getGains() {
        return currentPreset.getGains();
    }

    @Override
    public int getGainLimit() {
        return equalizer.getBandLevelRange()[1] / 100;
    }

    @Override
    public int getBandGain(int band) {
        return equalizer.getBandLevel((short) band) / 100;
    }

    @Override
    public void setBandGain(int band, int gain) {
        equalizer.setBandLevel((short)band, (short)(gain*100));
        currentPreset.getGains()[band] = gain;
    }

    @Override
    public void usePreset(EqualizerPreset preset) {
        for (int i = 0; i < getBandsCount(); i++) {
            setBandGain(i, preset.getGains()[i]);
        }
    }

    @Override
    public void saveSettings() {
        prefs.edit().putString("pref_current_preset", gson.toJson(currentPreset)).apply();
    }
}
