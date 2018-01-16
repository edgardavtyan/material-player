package com.edavtyan.materialplayer.components.detail.album_detail;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderModule;
import com.edavtyan.materialplayer.lib.theme.ThemeDaggerModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesModule;
import com.edavtyan.materialplayer.utils.UtilsModule;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		AlbumDetailModule.class,
		ParallaxHeaderModule.class,
		ActivityModulesModule.class,
		ThemeDaggerModule.class,
		ModelModulesModule.class,
		DbModule.class,
		AlbumArtModule.class,
		UtilsModule.class,
		CompactPrefsModule.class,
		AdvancedSharedPrefsModule.class})
public interface AlbumDetailComponent {
	void inject(AlbumDetailActivityNormal activity);
	void inject(AlbumDetailActivityCompact activity);
}
