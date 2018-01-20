package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   SearchTrackModule.class,
				   TrackListModule.class,
				   ModelModulesModule.class})
public interface SearchTrackComponent {
	void inject(SearchTrackFragment fragment);
}
