package com.edavtyan.materialplayer.components.detail.artist_detail;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderModule;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.lib.lastfm.LastFmFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.theme.ThemeFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;
import com.edavtyan.materialplayer.utils.UtilsFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		ArtistDetailFactory.class,
		ActivityModulesFactory.class,
		ThemeFactory.class,
		ParallaxHeaderModule.class,
		UtilsFactory.class,
		LastFmFactory.class,
		ModelModulesModule.class,
		DbModule.class,
		AdvancedSharedPrefsFactory.class,
		CompactPrefsModule.class})
public interface ArtistDetailComponent {
	void inject(ArtistDetailActivityNormal activity);
	void inject(ArtistDetailActivityCompact activity);
}
