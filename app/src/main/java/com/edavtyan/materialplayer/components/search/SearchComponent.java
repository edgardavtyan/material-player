package com.edavtyan.materialplayer.components.search;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.theme.ThemeFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SearchModule.class,
					  ActivityModulesFactory.class,
					  ThemeFactory.class,
					  UtilsFactory.class,
					  AdvancedSharedPrefsFactory.class})
public interface SearchComponent {
	void inject(SearchActivity activity);
}
