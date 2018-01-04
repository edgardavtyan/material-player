package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.components.audio_effects.amplifier.Amplifier;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoost;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audio_effects.surround.Surround;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

public class AudioEffectsModel {
	private final ModelServiceModule serviceModule;

	public AudioEffectsModel(ModelServiceModule serviceModule) {
		this.serviceModule = serviceModule;
	}

	public void setOnServiceConnectedListener(ModelServiceModule.OnServiceConnectedListener listener) {
		serviceModule.setOnServiceConnectedListener(listener);
	}

	public void init() {
		serviceModule.bind();
	}

	public void close() {
		serviceModule.unbind();
	}

	public boolean isConnected() {
		return serviceModule.getService() != null;
	}

	public Equalizer getEqualizer() {
		return serviceModule.getService().getEqualizer();
	}

	public BassBoost getBassBoost() {
		return serviceModule.getService().getBassBoost();
	}

	public Surround getSurround() {
		return serviceModule.getService().getSurround();
	}

	public Amplifier getAmplifier() {
		return serviceModule.getService().getAmplifier();
	}
}
