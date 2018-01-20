package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@ActivityScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				NowPlayingQueueModule.class,
				ModelModulesModule.class,
				ActivityModulesFactory.class,
				ThemeableActivityFactory.class})
public interface NowPlayingQueueComponent {
	void inject(NowPlayingQueueActivity activity);
}
