package com.edavtyan.materialplayer.screens.search.tracks;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   SearchTrackModule.class,
				   TrackListModule.class,
				   ModelModulesFactory.class})
public interface SearchTrackComponent {
	void inject(SearchTrackFragment fragment);
}
