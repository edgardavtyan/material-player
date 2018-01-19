package com.edavtyan.materialplayer.components.lists.artist_list;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.utils.UtilsFactory;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.lib.lastfm.LastFmFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		ArtistListModule.class,
		ModelModulesModule.class,
		UtilsFactory.class,
		DbModule.class,
		CompactPrefsModule.class,
		AdvancedSharedPrefsFactory.class,
		LastFmFactory.class})
public interface ArtistListComponent {
	void inject(ArtistListFragment fragment);
}
