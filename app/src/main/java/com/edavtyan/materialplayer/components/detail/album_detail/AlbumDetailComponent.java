package com.edavtyan.materialplayer.components.detail.album_detail;

import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.db.DaggerDBModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		AlbumDetailModule.class,
		ModelModulesModule.class,
		DaggerDBModule.class,
		AlbumArtModule.class,
		UtilsModule.class,
		AdvancedSharedPrefsModule.class})
public interface AlbumDetailComponent {
	void inject(AlbumDetailActivityNormal activity);
	void inject(AlbumDetailActivityCompact activity);
}
