package com.edavtyan.materialplayer.app.fragments.effects;

import com.edavtyan.materialplayer.app.R;

public class AmplifierFragment extends AudioEffectFragment {
    @Override
    public int getTitleId() {
        return R.string.amplifier_title;
    }

    @Override
    public SingleLevelEffectType getEffectType() {
        return SingleLevelEffectType.EFFECT_AMPLIFIER;
    }
}
