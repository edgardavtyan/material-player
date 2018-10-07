package com.edavtyan.materialplayer.ui.lists.playlist_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				PlaylistListFactory.class,
				PlaylistDialogsFactoryFragment.class,
				ModelModulesDIModule.class})
public interface PlaylistListFactoryComponent {
	void inject(PlaylistListFragment fragment);
}
