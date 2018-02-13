package com.edavtyan.materialplayer.ui.search.tracks;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   SearchTrackModule.class,
				   TrackListFactory.class,
				   ModelModulesFactory.class})
public interface SearchTrackComponent {
	void inject(SearchTrackFragment fragment);
}
