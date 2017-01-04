package com.edavtyan.materialplayer.components.audioeffects;

import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;

public class AudioEffectsPresenter
		implements AudioEffectsMvp.Presenter,
				   AudioEffectsMvp.Model.ServiceConnectionListener {

	private final AudioEffectsMvp.Model model;
	private final AudioEffectsMvp.View view;

	public AudioEffectsPresenter(AudioEffectsMvp.Model model, AudioEffectsMvp.View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void onCreate() {
		model.init();
		model.setOnServiceConnectedListener(this);
	}

	@Override
	public void onDestroy() {
		model.close();
	}

	@Override
	public void onEqualizerEnabledChanged(boolean enabled) {
		model.getEqualizer().setEnabled(enabled);
		model.getEqualizer().saveSettings();
	}

	@Override
	public void onEqualizerBandChanged(EqualizerBandView band) {
		model.getEqualizer().setBandGain(band.getIndex(), band.getGain());
	}

	@Override
	public void onEqualizerBandStopTracking() {
		model.getEqualizer().saveSettings();
	}

	@Override
	public void onBassBoostStrengthChanged(int strength) {
		model.getBassBoost().setStrength(strength);
	}

	@Override
	public void onBassBoostStrengthStopChanging() {
		model.getBassBoost().saveSettings();
	}

	@Override
	public void onSurroundStrengthChanged(int strength) {
		model.getSurround().setStrength(strength);
	}

	@Override
	public void onSurroundStrengthStopChanging() {
		model.getSurround().saveSettings();
	}

	@Override
	public void onAmplifierStrengthChanged(int gain) {
		model.getAmplifier().setGain(gain);
	}

	@Override
	public void onAmplifierStrengthStopChanging() {
		model.getAmplifier().saveSettings();
	}

	@Override
	public void onServiceConnected() {
		view.setEqualizerEnabled(model.getEqualizer().isEnabled());
		view.setEqualizerBands(
				model.getEqualizer().getBandsCount(),
				model.getEqualizer().getGainLimit(),
				model.getEqualizer().getFrequencies(),
				model.getEqualizer().getGains());
		view.initBassBoost(model.getBassBoost().getMaxStrength(), model.getBassBoost().getStrength());
		view.initSurround(model.getSurround().getMaxStrength(), model.getSurround().getStrength());
		view.initAmplifier(model.getAmplifier().getMaxGain(), model.getAmplifier().getGain());
	}
}
