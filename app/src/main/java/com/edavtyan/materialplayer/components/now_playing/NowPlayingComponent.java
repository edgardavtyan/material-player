package com.edavtyan.materialplayer.components.now_playing;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.lib.theme.ThemeModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesModule;
import com.edavtyan.materialplayer.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		NowPlayingModule.class,
		AlbumArtModule.class,
		UtilsModule.class,
		CompactPrefsModule.class,
		ActivityModulesModule.class,
		ThemeModule.class,
		AdvancedSharedPrefsModule.class})
public interface NowPlayingComponent {
	void inject(NowPlayingActivity activity);
}
