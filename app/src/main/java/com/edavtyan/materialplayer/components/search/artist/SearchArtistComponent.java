package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListModule;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.lib.lastfm.LastFmModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		SearchArtistModule.class,
		ArtistListModule.class,
		ModelModulesModule.class,
		CompactPrefsModule.class,
		UtilsModule.class,
		CompactPrefsModule.class,
		AdvancedSharedPrefsModule.class,
		DbModule.class,
		LastFmModule.class})
public interface SearchArtistComponent {
	void inject(SearchArtistFragment fragment);
}