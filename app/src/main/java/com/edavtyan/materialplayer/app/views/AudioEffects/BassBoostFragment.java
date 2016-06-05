package com.edavtyan.materialplayer.app.views.audioeffects;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.models.effects.SingleLevelEffectType;

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
