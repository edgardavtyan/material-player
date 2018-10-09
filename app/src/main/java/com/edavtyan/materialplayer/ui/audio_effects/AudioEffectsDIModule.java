package com.edavtyan.materialplayer.ui.audio_effects;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.audio_effects.presets.NewPresetDialog;
import com.edavtyan.materialplayer.ui.audio_effects.presets.PresetsSpinnerView;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import dagger.Module;
import dagger.Provides;

@Module
public class AudioEffectsDIModule {
	private final AudioEffectsActivity activity;

	public AudioEffectsDIModule(AudioEffectsActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public AudioEffectsPresenter providePresenter(AudioEffectsModel model) {
		return new AudioEffectsPresenter(model, activity);
	}

	@Provides
	@ActivityScope
	public AudioEffectsModel provideModel(ModelServiceModule serviceModule) {
		return new AudioEffectsModel(serviceModule);
	}

	@Provides
	@ActivityScope
	public NewPresetDialog provideNewPresetDialog(AudioEffectsPresenter presenter) {
		return new NewPresetDialog(activity, presenter);
	}

	@Provides
	@ActivityScope
	public PresetsSpinnerView providePresetsSpinnerView(AudioEffectsPresenter presenter) {
		return new PresetsSpinnerView(activity, presenter);
	}
}
