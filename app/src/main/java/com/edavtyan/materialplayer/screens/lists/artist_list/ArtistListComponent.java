package com.edavtyan.materialplayer.screens.lists.artist_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				ArtistListModule.class,
				ModelModulesFactory.class})
public interface ArtistListComponent {
	void inject(ArtistListFragment fragment);
}
