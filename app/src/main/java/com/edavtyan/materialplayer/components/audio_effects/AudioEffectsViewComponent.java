package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   AudioEffectsViewFactory.class,
				   ModelModulesModule.class,
				   ActivityModulesFactory.class,
				   ThemeableActivityFactory.class})
public interface AudioEffectsViewComponent {
	void inject(AudioEffectsActivity activity);
}
