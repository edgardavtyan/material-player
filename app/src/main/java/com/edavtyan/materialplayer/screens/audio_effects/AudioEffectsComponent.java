package com.edavtyan.materialplayer.screens.audio_effects;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   AudioEffectsFactory.class,
				   ModelModulesFactory.class,
				   ActivityModulesFactory.class,
				   ThemeableActivityFactory.class})
public interface AudioEffectsComponent {
	void inject(AudioEffectsActivity activity);
}
