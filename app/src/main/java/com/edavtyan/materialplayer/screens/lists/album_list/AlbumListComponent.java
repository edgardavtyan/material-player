package com.edavtyan.materialplayer.screens.lists.album_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {AlbumListFactory.class,
					  ModelModulesFactory.class})
public interface AlbumListComponent {
	void inject(AlbumListFragment fragment);
}
