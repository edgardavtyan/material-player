package com.edavtyan.materialplayer.components.lists.album_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {AlbumListModule.class,
					  ModelModulesFactory.class})
public interface AlbumListComponent {
	void inject(AlbumListFragment fragment);
}
