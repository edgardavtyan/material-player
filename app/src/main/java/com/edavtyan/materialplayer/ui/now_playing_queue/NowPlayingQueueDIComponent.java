package com.edavtyan.materialplayer.ui.now_playing_queue;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				NowPlayingQueueDIModule.class,
				ModelModulesDIModule.class,
				ThemeableFragmentDIModule.class})
public interface NowPlayingQueueDIComponent {
	void inject(NowPlayingQueueFragment activity);
}
