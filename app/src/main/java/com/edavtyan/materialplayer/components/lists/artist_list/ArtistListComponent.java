package com.edavtyan.materialplayer.components.lists.artist_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
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
