package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.theme.ThemeFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;
import com.edavtyan.materialplayer.utils.UtilsFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AudioEffectsViewFactory.class,
					  ModelModulesModule.class,
					  ActivityModulesFactory.class,
					  ThemeFactory.class,
					  UtilsFactory.class,
					  AdvancedSharedPrefsFactory.class})
public interface AudioEffectsViewComponent {
	void inject(AudioEffectsActivity activity);
}
