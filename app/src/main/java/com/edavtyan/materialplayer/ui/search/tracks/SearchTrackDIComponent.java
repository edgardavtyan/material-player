package com.edavtyan.materialplayer.ui.search.tracks;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.playlist.PlaylistDIModuleFragment;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   SearchTrackDIModule.class,
				   TrackListDIModule.class,
				   ModelModulesDIModule.class,
				   ThemeableFragmentDIModule.class,
				   PlaylistDIModuleFragment.class})
public interface SearchTrackDIComponent {
	void inject(SearchTrackFragment fragment);
}
