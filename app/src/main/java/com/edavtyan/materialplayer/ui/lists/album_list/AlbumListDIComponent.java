package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {AlbumListDIModule.class,
					  ModelModulesDIModule.class})
public interface AlbumListDIComponent {
	void inject(AlbumListFragment fragment);
}
