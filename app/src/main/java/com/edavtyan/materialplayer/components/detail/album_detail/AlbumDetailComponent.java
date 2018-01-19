package com.edavtyan.materialplayer.components.detail.album_detail;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.theme.ThemeFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		AlbumDetailFactory.class,
		ParallaxHeaderModule.class,
		ActivityModulesFactory.class,
		ThemeFactory.class,
		ModelModulesModule.class,
		DbModule.class,
		AlbumArtFactory.class,
		UtilsFactory.class,
		CompactPrefsModule.class,
		AdvancedSharedPrefsFactory.class})
public interface AlbumDetailComponent {
	void inject(AlbumDetailActivityNormal activity);
	void inject(AlbumDetailActivityCompact activity);
}
