package com.edavtyan.materialplayer.ui.detail.artist_detail;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderModule;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   ArtistDetailFactory.class,
				   ActivityModulesFactory.class,
				   ThemeableActivityFactory.class,
				   ParallaxHeaderModule.class,
				   ModelModulesFactory.class})
public interface ArtistDetailComponent {
	void inject(ArtistDetailActivity activity);
}
