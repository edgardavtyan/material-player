package com.edavtyan.materialplayer.components.main;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   MainModule.class,
				   ActivityModulesFactory.class,
				   ThemeableActivityFactory.class})
public interface MainComponent {
	void inject(MainActivity activity);
}
