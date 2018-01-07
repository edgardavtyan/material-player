package com.edavtyan.materialplayer.components.lists.album_list;

import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.db.DaggerDBModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AlbumListModule.class,
					  ModelModulesModule.class,
					  DaggerDBModule.class,
					  AdvancedSharedPrefsModule.class,
					  UtilsModule.class})
public interface AlbumListComponent {
	void inject(AlbumListFragment fragment);
}
