package com.edavtyan.materialplayer.ui.now_playing_bar;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   NowPlayingBarDIModule.class,
				   ThemeableFragmentDIModule.class})
public interface NowPlayingBarDIComponent {
	void inject(NowPlayingBarFragment fragment);
}
