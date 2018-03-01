package com.edavtyan.materialplayer.ui.search;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityDIModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDIComponent.class,
		   modules = {SearchDIModule.class,
					  ActivityModulesDIModule.class,
					  ThemeableActivityDIModule.class})
public interface SearchDIComponent {
	void inject(SearchActivity activity);
}
