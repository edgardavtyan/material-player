package com.edavtyan.materialplayer.app.music;

import lombok.AllArgsConstructor;
import lombok.Data;

public final class HQEqualizerStats {
    public static final int BANDS_COUNT = 10;
    public static final int GAIN_LIMIT = 15;
    public static final int DEFAULT_GAIN = 0;
    public static final int[] FREQUENCIES = new int[] {
            16000, 8000, 4000, 2000, 1000, 500, 250, 125, 60, 30
    };
    public static final Preset[] PRESETS = new Preset[] {
        new Preset(0, "Flat", new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
    };

    @Data @AllArgsConstructor
    public static final class Preset {
        private int index;
        private String name;
        private int[] gains;
    }

    private HQEqualizerStats() {}
}
