package com.edavtyan.materialplayer.components.detail.album_detail;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.ActivityScope;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderModule;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@ActivityScope
@Component(
		dependencies = {AppComponent.class},
		modules = {
				AlbumDetailFactory.class,
				ParallaxHeaderModule.class,
				ActivityModulesFactory.class,
				ThemeableActivityFactory.class,
				ModelModulesModule.class})
public interface AlbumDetailComponent {
	void inject(AlbumDetailActivityNormal activity);
	void inject(AlbumDetailActivityCompact activity);
}
