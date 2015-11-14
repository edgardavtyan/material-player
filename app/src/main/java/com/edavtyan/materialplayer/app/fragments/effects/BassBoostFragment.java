package com.edavtyan.materialplayer.app.fragments.effects;

import com.edavtyan.materialplayer.app.R;

public class BassBoostFragment extends AudioEffectFragment {
    @Override
    public int getTitleId() {
        return R.string.bassBoost_title;
    }

    @Override
    public SingleLevelEffectType getEffectType() {
        return SingleLevelEffectType.EFFECT_BASS_BOOST;
    }
}
