package com.edavtyan.materialplayer.lib.lastfm;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.utils.WebClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LastFmDIModule {
	@Provides
	@Singleton
	public LastfmApi provideApi(
			Context context,
			WebClient webClient,
			LastfmArtistInfoFileStorage fileStorage) {
		return new LastfmApi(webClient, fileStorage, context.getString(R.string.lastfm_api_key));
	}

	@Provides
	@Singleton
	public LastfmArtistInfoFileStorage provideArtistInfoFileStorage(Context context) {
		return new LastfmArtistInfoFileStorage(context);
	}
}
