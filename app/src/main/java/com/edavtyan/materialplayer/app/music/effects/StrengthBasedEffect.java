package com.edavtyan.materialplayer.app.music.effects;

public interface StrengthBasedEffect {
    void setEnabled(boolean isEnabled);
    int getMaxStrength();
    int getStrength();
    void saveSettings();
    void setStrength(int progress);
}
