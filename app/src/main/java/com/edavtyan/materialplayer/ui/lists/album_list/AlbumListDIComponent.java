package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistDialogsDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {AlbumListDIModule.class,
					  ModelModulesDIModule.class,
					  PlaylistDialogsDIModule.class,
					  ThemeableFragmentDIModule.class})
public interface AlbumListDIComponent {
	void inject(AlbumListFragment fragment);
}
