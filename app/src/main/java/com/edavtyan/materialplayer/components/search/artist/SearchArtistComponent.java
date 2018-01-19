package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.lib.lastfm.LastFmFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListModule;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		SearchArtistModule.class,
		ArtistListModule.class,
		ModelModulesModule.class,
		CompactPrefsModule.class,
		UtilsFactory.class,
		CompactPrefsModule.class,
		AdvancedSharedPrefsFactory.class,
		DbModule.class,
		LastFmFactory.class})
public interface SearchArtistComponent {
	void inject(SearchArtistFragment fragment);
}