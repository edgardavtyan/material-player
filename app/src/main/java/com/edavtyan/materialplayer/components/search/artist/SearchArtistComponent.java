package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   SearchArtistModule.class,
				   ArtistListModule.class,
				   ModelModulesModule.class})
public interface SearchArtistComponent {
	void inject(SearchArtistFragment fragment);
}