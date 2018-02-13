package com.edavtyan.materialplayer.ui.now_playing_queue;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@ActivityScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				NowPlayingQueueFactory.class,
				ModelModulesFactory.class,
				ActivityModulesFactory.class,
				ThemeableActivityFactory.class})
public interface NowPlayingQueueComponent {
	void inject(NowPlayingQueueActivity activity);
}
