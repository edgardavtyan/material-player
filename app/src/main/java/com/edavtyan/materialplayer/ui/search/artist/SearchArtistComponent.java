package com.edavtyan.materialplayer.ui.search.artist;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistDialogsDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   SearchArtistModule.class,
				   ArtistListDIModule.class,
				   ModelModulesDIModule.class,
				   ThemeableFragmentDIModule.class,
				   PlaylistDialogsDIModule.class})
public interface SearchArtistComponent {
	void inject(SearchArtistFragment fragment);
}