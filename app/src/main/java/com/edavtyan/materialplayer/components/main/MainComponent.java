package com.edavtyan.materialplayer.components.main;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.theme.ThemeFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		MainModule.class,
		CompactPrefsModule.class,
		ActivityModulesFactory.class,
		ThemeFactory.class,
		UtilsFactory.class,
		AdvancedSharedPrefsFactory.class})
public interface MainComponent {
	void inject(MainActivity activity);
}
