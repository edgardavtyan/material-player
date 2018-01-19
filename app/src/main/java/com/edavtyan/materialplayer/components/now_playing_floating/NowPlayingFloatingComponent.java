package com.edavtyan.materialplayer.components.now_playing_floating;

import com.edavtyan.materialplayer.lib.album_art.AlbumArtFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.theme.ThemeFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		NowPlayingFloatingFactory.class,
		AlbumArtFactory.class,
		UtilsFactory.class,
		AdvancedSharedPrefsFactory.class,
		ThemeFactory.class})
public interface NowPlayingFloatingComponent {
	void inject(NowPlayingFloatingFragment fragment);
}
