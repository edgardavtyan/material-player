package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.playlist.PlaylistFactoryFragment;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {AlbumListDIModule.class,
					  ModelModulesDIModule.class,
					  PlaylistFactoryFragment.class,
					  ThemeableFragmentDIModule.class})
public interface AlbumListDIComponent {
	void inject(AlbumListFragment fragment);
}
