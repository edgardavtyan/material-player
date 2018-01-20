package com.edavtyan.materialplayer.screens.prefs;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {PrefModule.class,
					  ActivityModulesFactory.class,
					  ThemeableActivityFactory.class})
public interface PrefComponent {
	void inject(PrefActivity activity);
}
