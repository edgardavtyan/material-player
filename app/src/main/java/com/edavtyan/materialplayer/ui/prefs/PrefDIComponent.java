package com.edavtyan.materialplayer.ui.prefs;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityDIModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDIComponent.class,
		   modules = {PrefDIModule.class,
					  ActivityModulesDIModule.class,
					  ThemeableActivityDIModule.class})
public interface PrefDIComponent {
	void inject(PrefActivity activity);
}
