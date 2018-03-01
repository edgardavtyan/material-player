package com.edavtyan.materialplayer.ui.audio_effects;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   AudioEffectsFactory.class,
				   ModelModulesDIModule.class,
				   ActivityModulesDIModule.class,
				   ThemeableActivityDIModule.class})
public interface AudioEffectsComponent {
	void inject(AudioEffectsActivity activity);
}
