package com.edavtyan.materialplayer.components.audioeffects.fragments;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.models.SingleLevelEffectType;

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
