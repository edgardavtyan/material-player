package com.edavtyan.materialplayer.components.search;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.lib.theme.ThemeDaggerModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesModule;
import com.edavtyan.materialplayer.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SearchModule.class,
					  ActivityModulesModule.class,
					  ThemeDaggerModule.class,
					  UtilsModule.class,
					  AdvancedSharedPrefsModule.class})
public interface SearchComponent {
	void inject(SearchActivity activity);
}
