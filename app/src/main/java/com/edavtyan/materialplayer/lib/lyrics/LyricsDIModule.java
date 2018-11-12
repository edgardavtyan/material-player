package com.edavtyan.materialplayer.lib.lyrics;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LyricsDIModule {
	@Provides
	@Singleton
	public LyricsProvider provideLyricsProvider(LyricsApi api, LyricsStorage storage) {
		return new LyricsProvider(api, storage);
	}

	@Provides
	@Singleton
	public LyricsApi provideLyricsApi() {
		return new LyricsApi();
	}

	@Provides
	@Singleton
	public LyricsStorage provideLyricsStorage(Context context) {
		return new LyricsStorage(context);
	}
}
