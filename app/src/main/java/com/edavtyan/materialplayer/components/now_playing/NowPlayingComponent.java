package com.edavtyan.materialplayer.components.now_playing;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.theme.ThemeFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		NowPlayingModule.class,
		AlbumArtFactory.class,
		UtilsFactory.class,
		CompactPrefsModule.class,
		ActivityModulesFactory.class,
		ThemeFactory.class,
		AdvancedSharedPrefsFactory.class})
public interface NowPlayingComponent {
	void inject(NowPlayingActivity activity);
}
