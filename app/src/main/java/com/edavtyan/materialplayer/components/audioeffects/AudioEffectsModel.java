package com.edavtyan.materialplayer.components.audioeffects;

import com.edavtyan.materialplayer.components.audioeffects.amplifier.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.bassboost.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.surround.Surround;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

public class AudioEffectsModel implements AudioEffectsMvp.Model {
	private final ModelServiceModule serviceModule;

	public AudioEffectsModel(ModelServiceModule serviceModule) {
		this.serviceModule = serviceModule;
	}

	@Override
	public void setOnServiceConnectedListener(ModelServiceModule.OnServiceConnectedListener listener) {
		serviceModule.setOnServiceConnectedListener(listener);
	}

	@Override
	public void init() {
		serviceModule.bind();
	}

	@Override
	public void close() {
		serviceModule.unbind();
	}

	@Override
	public boolean isConnected() {
		return serviceModule.getService() != null;
	}

	@Override
	public Equalizer getEqualizer() {
		return serviceModule.getService().getEqualizer();
	}

	@Override
	public BassBoost getBassBoost() {
		return serviceModule.getService().getBassBoost();
	}

	@Override
	public Surround getSurround() {
		return serviceModule.getService().getSurround();
	}

	@Override
	public Amplifier getAmplifier() {
		return serviceModule.getService().getAmplifier();
	}
}
