package com.edavtyan.materialplayer.components.now_playing_floating;

import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.lib.theme.ThemeDaggerModule;
import com.edavtyan.materialplayer.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		NowPlayingFloatingModule.class,
		AlbumArtModule.class,
		UtilsModule.class,
		AdvancedSharedPrefsModule.class,
		ThemeDaggerModule.class})
public interface NowPlayingFloatingComponent {
	void inject(NowPlayingFloatingFragment fragment);
}
