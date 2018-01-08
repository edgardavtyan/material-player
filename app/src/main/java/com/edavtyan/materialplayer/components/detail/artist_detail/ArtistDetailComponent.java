package com.edavtyan.materialplayer.components.detail.artist_detail;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.utils.UtilsModule;
import com.edavtyan.materialplayer.db.DbModule;
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
		DbModule.class,
		AdvancedSharedPrefsModule.class,
		CompactPrefsModule.class})
public interface ArtistDetailComponent {
	void inject(ArtistDetailActivityNormal activity);
	void inject(ArtistDetailActivityCompact activity);
}
