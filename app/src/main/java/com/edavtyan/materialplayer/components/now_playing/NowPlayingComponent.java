package com.edavtyan.materialplayer.components.now_playing;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		NowPlayingModule.class,
		AlbumArtModule.class,
		UtilsModule.class,
		CompactPrefsModule.class})
public interface NowPlayingComponent {
	void inject(NowPlayingActivity activity);
}
