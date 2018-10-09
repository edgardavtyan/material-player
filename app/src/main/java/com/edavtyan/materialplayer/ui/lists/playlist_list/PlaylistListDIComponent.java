package com.edavtyan.materialplayer.ui.lists.playlist_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.playlist.PlaylistDIModuleFragment;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				PlaylistListDIModule.class,
				PlaylistDIModuleFragment.class,
				ModelModulesDIModule.class,
				ThemeableFragmentDIModule.class})
public interface PlaylistListDIComponent {
	void inject(PlaylistListFragment fragment);
}
