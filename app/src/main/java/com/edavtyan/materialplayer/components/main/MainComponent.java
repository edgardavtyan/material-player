package com.edavtyan.materialplayer.components.main;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.lib.theme.ThemeDaggerModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesModule;
import com.edavtyan.materialplayer.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		MainModule.class,
		CompactPrefsModule.class,
		ActivityModulesModule.class,
		ThemeDaggerModule.class,
		UtilsModule.class,
		AdvancedSharedPrefsModule.class})
public interface MainComponent {
	void inject(MainActivity activity);
}
