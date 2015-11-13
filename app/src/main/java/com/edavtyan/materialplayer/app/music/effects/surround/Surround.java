package com.edavtyan.materialplayer.app.music.effects.surround;

public interface Surround {
    void setEnabled(boolean isEnabled);
    int getMaxStrength();
    int getStrength();
    void saveSettings();
    void setStrength(int progress);
}
