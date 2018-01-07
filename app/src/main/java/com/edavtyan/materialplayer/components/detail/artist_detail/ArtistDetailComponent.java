package com.edavtyan.materialplayer.components.detail.artist_detail;

import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.db.DaggerDBModule;
import com.edavtyan.materialplayer.lib.lastfm.LastFmModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		ArtistDetailModule.class,
		UtilsModule.class,
		LastFmModule.class,
		ModelModulesModule.class,
		DaggerDBModule.class,
		AdvancedSharedPrefsModule.class})
public interface ArtistDetailComponent {
	void inject(ArtistDetailActivityNormal activity);
	void inject(ArtistDetailActivityCompact activity);
}
