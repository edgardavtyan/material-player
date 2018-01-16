package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.lib.theme.ThemeDaggerModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;
import com.edavtyan.materialplayer.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AudioEffectsViewModule.class,
					  ModelModulesModule.class,
					  ActivityModulesModule.class,
					  ThemeDaggerModule.class,
					  UtilsModule.class,
					  AdvancedSharedPrefsModule.class})
public interface AudioEffectsViewComponent {
	void inject(AudioEffectsActivity activity);
}
