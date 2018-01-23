package com.edavtyan.materialplayer.screens.lists.track_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				TrackListFactory.class,
				ModelModulesFactory.class})
public interface TrackListComponent {
	void inject(TrackListFragment fragment);
}
