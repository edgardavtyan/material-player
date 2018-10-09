package com.edavtyan.materialplayer.lib.playlist.models;

import android.content.Context;

import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistModelsDIModule {
	@Provides
	@Singleton
	public PlaylistStorage providePlaylistStorage(Context context, Gson gson) {
		return new PlaylistStorage(context, gson);
	}

	@Provides
	@Singleton
	public PlaylistManager providePlaylistManager(
			PlaylistStorage storage,
			ModelServiceModule serviceModule) {
		return new PlaylistManager(storage, serviceModule);
	}

	@Provides
	@Singleton
	public ModelServiceModule provideModelServiceModule(Context context) {
		return new ModelServiceModule(context);
	}
}
