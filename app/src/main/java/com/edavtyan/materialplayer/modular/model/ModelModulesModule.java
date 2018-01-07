package com.edavtyan.materialplayer.modular.model;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModulesModule {
	@Provides
	public ModelServiceModule provideModelServiceModule(Context context) {
		return new ModelServiceModule(context);
	}
}
