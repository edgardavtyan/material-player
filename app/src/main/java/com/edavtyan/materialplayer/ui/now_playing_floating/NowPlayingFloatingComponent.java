package com.edavtyan.materialplayer.ui.now_playing_floating;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentFactory;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   NowPlayingFloatingFactory.class,
				   ThemeableFragmentFactory.class})
public interface NowPlayingFloatingComponent {
	void inject(NowPlayingFloatingFragment fragment);
}
