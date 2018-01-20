package com.edavtyan.materialplayer.components.lists.album_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {AlbumListModule.class,
					  ModelModulesModule.class})
public interface AlbumListComponent {
	void inject(AlbumListFragment fragment);
}
