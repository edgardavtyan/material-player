package com.edavtyan.materialplayer.components.audioeffects;

import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;
import com.edavtyan.materialplayer.components.player.PlayerService;

public class AudioEffectsPresenter implements AudioEffectsMvp.Presenter {

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
		if (model.isConnected() == false) {
			return;
		}

		model.getEqualizer().setEnabled(enabled);
		model.getEqualizer().saveSettings();
	}

	@Override
	public void onEqualizerBandChanged(EqualizerBandView band) {
		model.getEqualizer().setBandGain(band.getIndex(), band.getGain());
		view.setEqualizerPresetAsCustomNew();
	}

	@Override
	public void onEqualizerBandStopTracking() {
		model.getEqualizer().saveSettings();
	}

	@Override
	public void onPresetSelected(int position) {
		if (position == 0) {
			view.setDeletePresetButtonEnabled(false);
			return;
		}

		int customPresetsCount = model.getEqualizer().getCustomPresetNames().size();
		if (1 <= position && position <= customPresetsCount) {
			view.setDeletePresetButtonEnabled(true);
		} else {
			view.setDeletePresetButtonEnabled(false);
		}

		model.getEqualizer().useBuiltInPreset((short) position);
		view.setEqualizerBands(
				model.getEqualizer().getBandsCount(),
				model.getEqualizer().getGainLimit(),
				model.getEqualizer().getFrequencies(),
				model.getEqualizer().getGains());
	}

	@Override
	public void onCreateNewPreset(String name) {
		model.getEqualizer().createNewPreset(name);
	}

	@Override
	public void onDeletePreset(int position) {
		model.getEqualizer().deletePreset(position);
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
	public void onServiceConnected(PlayerService service) {
		view.setEqualizerEnabled(model.getEqualizer().isEnabled());
		view.setEqualizerBands(
				model.getEqualizer().getBandsCount(),
				model.getEqualizer().getGainLimit(),
				model.getEqualizer().getFrequencies(),
				model.getEqualizer().getGains());
		view.setEqualizerPresets(
				model.getEqualizer().getBuiltInPresetNames(),
				model.getEqualizer().getCustomPresetNames());
		view.setCurrentEqualizerPreset(model.getEqualizer().getCurrentBuiltInPresetIndex());
		view.initBassBoost(model.getBassBoost().getMaxStrength(), model.getBassBoost().getStrength());
		view.initSurround(model.getSurround().getMaxStrength(), model.getSurround().getStrength());
		view.initAmplifier(model.getAmplifier().getMaxGain(), model.getAmplifier().getGain());
	}
}
