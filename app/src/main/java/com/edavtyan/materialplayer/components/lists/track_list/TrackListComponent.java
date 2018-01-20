package com.edavtyan.materialplayer.components.lists.track_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				TrackListModule.class,
				ModelModulesModule.class})
public interface TrackListComponent {
	void inject(TrackListFragment fragment);
}
