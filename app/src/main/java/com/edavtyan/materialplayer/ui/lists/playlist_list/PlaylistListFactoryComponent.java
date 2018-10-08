package com.edavtyan.materialplayer.ui.lists.playlist_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.playlist.PlaylistFactoryFragment;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				PlaylistListFactory.class,
				PlaylistFactoryFragment.class,
				ModelModulesDIModule.class,
				ThemeableFragmentDIModule.class})
public interface PlaylistListFactoryComponent {
	void inject(PlaylistListFragment fragment);
}
