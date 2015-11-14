package com.edavtyan.materialplayer.app.fragments.effects;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.effects.SingleLevelEffectType;

public class SurroundFragment extends AudioEffectFragment {
    @Override
    public int getTitleId() {
        return R.string.surround_title;
    }

    @Override
    public SingleLevelEffectType getEffectType() {
        return SingleLevelEffectType.EFFECT_SURROUND;
    }
}

