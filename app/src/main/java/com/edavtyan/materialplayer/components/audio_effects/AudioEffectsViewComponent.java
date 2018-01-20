package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   AudioEffectsViewFactory.class,
				   ModelModulesFactory.class,
				   ActivityModulesFactory.class,
				   ThemeableActivityFactory.class})
public interface AudioEffectsViewComponent {
	void inject(AudioEffectsActivity activity);
}
