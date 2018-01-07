package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.NewPresetDialog;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.PresetsSpinnerView;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AudioEffectsViewModule {
	private final AudioEffectsActivity view;

	public AudioEffectsViewModule(AudioEffectsActivity view) {
		this.view = view;
	}

	@Provides
	@Singleton
	public AudioEffectsPresenter providePresenter(AudioEffectsModel model) {
		return new AudioEffectsPresenter(model, view);
	}

	@Provides
	@Singleton
	public AudioEffectsModel provideModel(ModelServiceModule serviceModule) {
		return new AudioEffectsModel(serviceModule);
	}

	@Provides
	@Singleton
	public NewPresetDialog provideNewPresetDialog(AudioEffectsPresenter presenter) {
		return new NewPresetDialog(view, presenter);
	}

	@Provides
	@Singleton
	public PresetsSpinnerView providePresetsSpinnerView(AudioEffectsPresenter presenter) {
		return new PresetsSpinnerView(view, presenter);
	}
}
