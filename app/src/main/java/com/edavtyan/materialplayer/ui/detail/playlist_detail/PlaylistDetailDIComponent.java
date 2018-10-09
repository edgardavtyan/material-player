package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {PlaylistDetailDIModule.class,
				   ActivityModulesDIModule.class,
				   ThemeableActivityDIModule.class,
				   ModelModulesDIModule.class})
public interface PlaylistDetailDIComponent {
	void inject(PlaylistDetailActivity activity);
}
