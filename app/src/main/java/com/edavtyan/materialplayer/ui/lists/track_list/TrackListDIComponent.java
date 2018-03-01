package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				TrackListDIModule.class,
				ModelModulesDIModule.class})
public interface TrackListDIComponent {
	void inject(TrackListFragment fragment);
}
