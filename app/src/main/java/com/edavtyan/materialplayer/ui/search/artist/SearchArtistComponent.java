package com.edavtyan.materialplayer.ui.search.artist;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   SearchArtistModule.class,
				   ArtistListFactory.class,
				   ModelModulesFactory.class})
public interface SearchArtistComponent {
	void inject(SearchArtistFragment fragment);
}