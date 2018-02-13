package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
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
