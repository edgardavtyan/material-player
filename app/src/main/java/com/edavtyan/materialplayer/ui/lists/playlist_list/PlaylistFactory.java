package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistFactory {
	@Provides
	@Singleton
	public PlaylistStorage providePlaylistStorage(Context context, Gson gson) {
		return new PlaylistStorage(context, gson);
	}

	@Provides
	@Singleton
	public PlaylistManager providePlaylistManager(PlaylistStorage storage) {
		return new PlaylistManager(storage);
	}
}
