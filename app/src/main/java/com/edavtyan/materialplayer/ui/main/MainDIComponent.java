package com.edavtyan.materialplayer.ui.main;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.ui.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   MainDIModule.class,
				   ActivityModulesDIModule.class,
				   ThemeableActivityDIModule.class})
public interface MainDIComponent {
	void inject(MainActivity activity);
}
