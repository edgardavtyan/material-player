package com.edavtyan.materialplayer.ui.lists.artist_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				ArtistListFactory.class,
				ModelModulesFactory.class})
public interface ArtistListComponent {
	void inject(ArtistListFragment fragment);
}
