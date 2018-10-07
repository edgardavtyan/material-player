package com.edavtyan.materialplayer.ui.detail.artist_detail;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderDIModule;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistDialogsActivityDIModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   ArtistDetailDIModule.class,
				   ActivityModulesDIModule.class,
				   ThemeableActivityDIModule.class,
				   ParallaxHeaderDIModule.class,
				   ModelModulesDIModule.class,
				   PlaylistDialogsActivityDIModule.class})
public interface ArtistDetailDIComponent {
	void inject(ArtistDetailActivity activity);
}
