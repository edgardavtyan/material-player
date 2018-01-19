package com.edavtyan.materialplayer.components.prefs;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.theme.ThemeFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PrefModule.class,
					  ActivityModulesFactory.class,
					  ThemeFactory.class,
					  UtilsFactory.class,
					  AdvancedSharedPrefsFactory.class})
public interface PrefComponent {
	void inject(PrefActivity activity);
}
