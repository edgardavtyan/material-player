package com.edavtyan.materialplayer.components.audio_effects;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.NewPresetDialog;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.PresetsSpinnerView;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AudioEffectsViewFactory {
	private final AudioEffectsActivity activity;

	public AudioEffectsViewFactory(AudioEffectsActivity activity) {
		this.activity = activity;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return activity;
	}

	@Provides
	@Singleton
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@Singleton
	public AudioEffectsPresenter providePresenter(AudioEffectsModel model) {
		return new AudioEffectsPresenter(model, activity);
	}

	@Provides
	@Singleton
	public AudioEffectsModel provideModel(ModelServiceModule serviceModule) {
		return new AudioEffectsModel(serviceModule);
	}

	@Provides
	@Singleton
	public NewPresetDialog provideNewPresetDialog(AudioEffectsPresenter presenter) {
		return new NewPresetDialog(activity, presenter);
	}

	@Provides
	@Singleton
	public PresetsSpinnerView providePresetsSpinnerView(AudioEffectsPresenter presenter) {
		return new PresetsSpinnerView(activity, presenter);
	}
}
