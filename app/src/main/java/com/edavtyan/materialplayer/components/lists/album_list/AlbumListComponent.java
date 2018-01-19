package com.edavtyan.materialplayer.components.lists.album_list;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AlbumListModule.class,
					  ModelModulesModule.class,
					  DbModule.class,
					  AdvancedSharedPrefsFactory.class,
					  UtilsFactory.class,
					  CompactPrefsModule.class})
public interface AlbumListComponent {
	void inject(AlbumListFragment fragment);
}
