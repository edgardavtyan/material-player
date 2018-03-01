package com.edavtyan.materialplayer.ui.search.tracks;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   SearchTrackModule.class,
				   TrackListDIModule.class,
				   ModelModulesDIModule.class})
public interface SearchTrackComponent {
	void inject(SearchTrackFragment fragment);
}
