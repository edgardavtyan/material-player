package com.edavtyan.materialplayer.components.lists.artist_list;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.lib.lastfm.LastFmModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		ArtistListModule.class,
		ModelModulesModule.class,
		UtilsModule.class,
		DbModule.class,
		CompactPrefsModule.class,
		AdvancedSharedPrefsModule.class,
		LastFmModule.class})
public interface ArtistListComponent {
	void inject(ArtistListFragment fragment);
}
