package com.edavtyan.materialplayer.ui.lists.artist_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.lib.playlist.PlaylistDIModuleFragment;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				ArtistListDIModule.class,
				ModelModulesDIModule.class,
				PlaylistDIModuleFragment.class,
				ThemeableFragmentDIModule.class})
public interface ArtistListDIComponent {
	void inject(ArtistListFragment fragment);
}
