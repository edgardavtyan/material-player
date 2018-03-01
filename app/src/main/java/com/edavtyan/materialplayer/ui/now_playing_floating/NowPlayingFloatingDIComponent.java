package com.edavtyan.materialplayer.ui.now_playing_floating;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   NowPlayingFloatingDIModule.class,
				   ThemeableFragmentDIModule.class})
public interface NowPlayingFloatingDIComponent {
	void inject(NowPlayingFloatingFragment fragment);
}
