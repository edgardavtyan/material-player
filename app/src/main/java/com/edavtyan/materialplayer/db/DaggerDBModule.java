package com.edavtyan.materialplayer.db;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerDBModule {
	@Provides
	@Singleton
	public ArtistDB provideArtistDB(Context context) {
		return new ArtistDB(context);
	}

	@Provides
	@Singleton
	public AlbumDB provideAlbumDB(Context context) {
		return new AlbumDB(context);
	}

	@Provides
	@Singleton
	public TrackDB provideTrackDB(Context context) {
		return new TrackDB(context);
	}
}
