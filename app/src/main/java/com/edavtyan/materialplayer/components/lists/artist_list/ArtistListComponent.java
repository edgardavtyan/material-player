package com.edavtyan.materialplayer.components.lists.artist_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				ArtistListModule.class,
				ModelModulesModule.class})
public interface ArtistListComponent {
	void inject(ArtistListFragment fragment);
}
