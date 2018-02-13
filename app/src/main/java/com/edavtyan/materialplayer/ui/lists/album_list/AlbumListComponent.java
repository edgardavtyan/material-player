package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,
		   modules = {AlbumListFactory.class,
					  ModelModulesFactory.class})
public interface AlbumListComponent {
	void inject(AlbumListFragment fragment);
}
