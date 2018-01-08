package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.components.lists.album_list.AlbumListModule;
import com.edavtyan.materialplayer.db.DaggerDBModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		SearchAlbumModule.class,
		AlbumListModule.class,
		ModelModulesModule.class,
		CompactPrefsModule.class,
		UtilsModule.class,
		CompactPrefsModule.class,
		AdvancedSharedPrefsModule.class,
		DaggerDBModule.class})
public interface SearchAlbumComponent {
	void inject(SearchAlbumFragment fragment);
}
