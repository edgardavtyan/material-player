package com.edavtyan.materialplayer.app.music.effects.equalizer;

public interface Equalizer {
    int getBandsCount();
    int getBandFrequency(int band);
    int getBandGain(int band);
    void setBandGain(int band, int gain);
    void saveSettings();
    void usePreset(EqualizerPreset preset);
    int getGainLimit();
}
