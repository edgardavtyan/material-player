package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   SearchArtistModule.class,
				   ArtistListModule.class,
				   ModelModulesFactory.class})
public interface SearchArtistComponent {
	void inject(SearchArtistFragment fragment);
}