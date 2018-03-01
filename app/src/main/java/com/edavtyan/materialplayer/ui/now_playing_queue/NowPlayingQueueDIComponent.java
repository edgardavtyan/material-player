package com.edavtyan.materialplayer.ui.now_playing_queue;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				NowPlayingQueueDIModule.class,
				ModelModulesDIModule.class,
				ActivityModulesDIModule.class,
				ThemeableActivityDIModule.class})
public interface NowPlayingQueueDIComponent {
	void inject(NowPlayingQueueActivity activity);
}
