package com.edavtyan.materialplayer.views.audioeffects;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.models.effects.SingleLevelEffectType;

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
