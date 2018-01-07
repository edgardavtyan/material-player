package com.edavtyan.materialplayer.lib.lastfm;

import android.content.Context;

import com.edavtyan.materialplayer.utils.WebClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LastFmModule {
	private final String apiKey;

	public LastFmModule(String apiKey) {
		this.apiKey = apiKey;
	}

	@Provides
	@Singleton
	public String provideApiKey() {
		return apiKey;
	}

	@Provides
	@Singleton
	public LastfmApi provideApi(
			WebClient webClient,
			LastfmArtistInfoFileStorage fileStorage,
			String apiKey) {
		return new LastfmApi(webClient, fileStorage, apiKey);
	}

	@Provides
	@Singleton
	public LastfmArtistInfoFileStorage provideArtistInfoFileStorage(Context context) {
		return new LastfmArtistInfoFileStorage(context);
	}
}
