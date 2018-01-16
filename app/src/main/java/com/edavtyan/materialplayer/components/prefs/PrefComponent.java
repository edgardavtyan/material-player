package com.edavtyan.materialplayer.components.prefs;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.lib.theme.ThemeModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesModule;
import com.edavtyan.materialplayer.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PrefModule.class,
					  ActivityModulesModule.class,
					  ThemeModule.class,
					  UtilsModule.class,
					  AdvancedSharedPrefsModule.class})
public interface PrefComponent {
	void inject(PrefActivity activity);
}
