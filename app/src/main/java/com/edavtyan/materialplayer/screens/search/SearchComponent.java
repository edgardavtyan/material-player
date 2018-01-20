package com.edavtyan.materialplayer.screens.search;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {SearchModule.class,
					  ActivityModulesFactory.class,
					  ThemeableActivityFactory.class})
public interface SearchComponent {
	void inject(SearchActivity activity);
}
