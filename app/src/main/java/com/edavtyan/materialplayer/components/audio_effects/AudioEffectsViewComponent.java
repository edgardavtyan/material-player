package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AudioEffectsViewModule.class, ModelModulesModule.class})
public interface AudioEffectsViewComponent {
	void inject(AudioEffectsActivity activity);
}
