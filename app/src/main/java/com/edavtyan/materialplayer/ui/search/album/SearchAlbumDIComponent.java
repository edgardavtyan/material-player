package com.edavtyan.materialplayer.ui.search.album;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.playlist.PlaylistDIModuleFragment;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListDIModule;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				SearchAlbumDIModule.class,
				AlbumListDIModule.class,
				ModelModulesDIModule.class,
				PlaylistDIModuleFragment.class,
				ThemeableFragmentDIModule.class})
public interface SearchAlbumDIComponent {
	void inject(SearchAlbumFragment fragment);
}
