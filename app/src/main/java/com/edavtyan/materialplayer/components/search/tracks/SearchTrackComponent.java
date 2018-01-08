package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.utils.UtilsModule;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListModule;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		SearchTrackModule.class,
		ModelModulesModule.class,
		DbModule.class,
		CompactPrefsModule.class,
		TrackListModule.class,
		UtilsModule.class,
		AdvancedSharedPrefsModule.class})
public interface SearchTrackComponent {
	void inject(SearchTrackFragment fragment);
}
