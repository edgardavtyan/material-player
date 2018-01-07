package com.edavtyan.materialplayer.modular.model;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModulesModule {
	private final Context context;

	public ModelModulesModule(Context context) {
		this.context = context;
	}

	@Provides
	public ModelServiceModule provideModelServiceModule() {
		return new ModelServiceModule(context);
	}
}
