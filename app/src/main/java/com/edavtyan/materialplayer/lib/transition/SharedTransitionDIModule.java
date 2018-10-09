package com.edavtyan.materialplayer.lib.transition;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedTransitionDIModule {
	@Provides
	@Singleton
	public SharedTransitionsManager provideManager() {
		return new SharedTransitionsManager();
	}
}
