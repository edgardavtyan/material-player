package com.edavtyan.materialplayer.transition;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedTransitionDaggerModule {
	@Provides
	@Singleton
	public SharedTransitionsManager provideManager() {
		return new SharedTransitionsManager();
	}
}
