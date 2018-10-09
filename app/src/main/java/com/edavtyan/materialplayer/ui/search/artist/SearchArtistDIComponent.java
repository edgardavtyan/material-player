package com.edavtyan.materialplayer.ui.search.artist;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.playlist.PlaylistDIModuleFragment;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   SearchArtistDIModule.class,
				   ArtistListDIModule.class,
				   ModelModulesDIModule.class,
				   ThemeableFragmentDIModule.class,
				   PlaylistDIModuleFragment.class})
public interface SearchArtistDIComponent {
	void inject(SearchArtistFragment fragment);
}