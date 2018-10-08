package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.playlist.PlaylistFactoryFragment;
import com.edavtyan.materialplayer.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				TrackListDIModule.class,
				ModelModulesDIModule.class,
				ThemeableFragmentDIModule.class,
				PlaylistFactoryFragment.class})
public interface TrackListDIComponent {
	void inject(TrackListFragment fragment);
}
