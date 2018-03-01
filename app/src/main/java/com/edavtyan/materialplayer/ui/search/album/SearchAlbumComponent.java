package com.edavtyan.materialplayer.ui.search.album;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				SearchAlbumModule.class,
				AlbumListDIModule.class,
				ModelModulesDIModule.class})
public interface SearchAlbumComponent {
	void inject(SearchAlbumFragment fragment);
}
