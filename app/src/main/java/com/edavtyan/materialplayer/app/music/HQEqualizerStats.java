package com.edavtyan.materialplayer.app.music;

import com.h6ah4i.android.media.audiofx.IEqualizer;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerContext;
import com.h6ah4i.android.media.opensl.audiofx.OpenSLHQEqualizer;

import lombok.Data;

public final class HQEqualizerStats {
    public static final int BANDS_COUNT;
    public static final int MAX_GAIN;
    public static final int MIN_GAIN;
    public static final int[] CENTER_FREQUENCIES;
    public static final Preset[] PRESETS;

    @Data
    public static final class Preset {
        private int index;
        private String name;
        private IEqualizer.Settings settings;
    }

    static {
        OpenSLMediaPlayerContext.Parameters params = new OpenSLMediaPlayerContext.Parameters();
        params.options = OpenSLMediaPlayerContext.OPTION_USE_HQ_EQUALIZER;

        OpenSLMediaPlayerContext playerContext = new OpenSLMediaPlayerContext(null, params);

        IEqualizer equalizer = new OpenSLHQEqualizer(playerContext);
        BANDS_COUNT = equalizer.getNumberOfBands();
        MIN_GAIN = equalizer.getBandLevelRange()[0];
        MAX_GAIN = equalizer.getBandLevelRange()[1];

        CENTER_FREQUENCIES = new int[BANDS_COUNT];
        for (short i = 0; i < BANDS_COUNT; i++) {
            CENTER_FREQUENCIES[i] = equalizer.getCenterFreq(i);
        }

        PRESETS = new Preset[equalizer.getNumberOfPresets()];
        for (short i = 0; i < PRESETS.length; i++) {
            equalizer.usePreset(i);
            Preset preset = new Preset();
            preset.setIndex(i);
            preset.setName(equalizer.getPresetName(i));
            preset.setSettings(equalizer.getProperties());
            PRESETS[i] = preset;
        }

        playerContext.release();
        equalizer.release();
    }

    private HQEqualizerStats() {}
}
