package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistDIModule {
	@Provides
	@Singleton
	public PlaylistStorage providePlaylistStorage(Context context) {
		return new PlaylistStorage(context);
	}

	@Provides
	@Singleton
	public PlaylistManager providePlaylistManager(PlaylistStorage storage) {
		return new PlaylistManager(storage);
	}
}
