package com.edavtyan.materialplayer.ui.lists.artist_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.lib.playlist.PlaylistFactoryFragment;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				ArtistListDIModule.class,
				ModelModulesDIModule.class,
				PlaylistFactoryFragment.class,
				ThemeableFragmentDIModule.class})
public interface ArtistListDIComponent {
	void inject(ArtistListFragment fragment);
}
