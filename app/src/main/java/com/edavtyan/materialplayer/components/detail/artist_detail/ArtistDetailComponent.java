package com.edavtyan.materialplayer.components.detail.artist_detail;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.ActivityScope;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderModule;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   ArtistDetailFactory.class,
				   ActivityModulesFactory.class,
				   ThemeableActivityFactory.class,
				   ParallaxHeaderModule.class,
				   ModelModulesModule.class})
public interface ArtistDetailComponent {
	void inject(ArtistDetailActivityNormal activity);
	void inject(ArtistDetailActivityCompact activity);
}
