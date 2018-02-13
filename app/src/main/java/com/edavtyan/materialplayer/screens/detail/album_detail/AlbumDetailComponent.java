package com.edavtyan.materialplayer.screens.detail.album_detail;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderModule;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@ActivityScope
@Component(
		dependencies = {AppComponent.class},
		modules = {
				AlbumDetailFactory.class,
				ParallaxHeaderModule.class,
				ActivityModulesFactory.class,
				ThemeableActivityFactory.class,
				ModelModulesFactory.class})
public interface AlbumDetailComponent {
	void inject(AlbumDetailActivity activity);
}
