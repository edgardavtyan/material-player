package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.components.lists.album_list.AlbumListModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				SearchAlbumModule.class,
				AlbumListModule.class,
				ModelModulesModule.class})
public interface SearchAlbumComponent {
	void inject(SearchAlbumFragment fragment);
}
