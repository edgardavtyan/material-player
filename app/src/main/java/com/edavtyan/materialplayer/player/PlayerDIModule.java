package com.edavtyan.materialplayer.player;

import android.content.Context;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.player.engines.ExtendedAudioEngine;
import com.edavtyan.materialplayer.player.engines.OpenSLAudioEngine;
import com.edavtyan.materialplayer.player.managers.AudioFocusManager;
import com.edavtyan.materialplayer.player.managers.MediaSessionManager;
import com.edavtyan.materialplayer.service.PlayerServiceScope;
import com.google.gson.Gson;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerFactory;

import net.protyposis.android.mediaplayer.MediaPlayer;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerDIModule {
	@Provides
	@PlayerServiceScope
	public int provideSessionId(Player player) {
		return player.getSessionId();
	}

	@Provides
	@PlayerServiceScope
	public Player providePlayer(
			ExtendedAudioEngine extendedAudioEngine,
			OpenSLAudioEngine openSLAudioEngine,
			PlayerQueue queue,
			PlayerPrefs prefs,
			PlayerQueueStorage queueStorage) {
		return new Player(extendedAudioEngine, openSLAudioEngine, queue, prefs, queueStorage);
	}

	@Provides
	@PlayerServiceScope
	public ExtendedAudioEngine provideExtendedAudioEngine() {
		return new ExtendedAudioEngine(new MediaPlayer());
	}

	@Provides
	@PlayerServiceScope
	public PlayerQueue providePlayerQueue() {
		return new PlayerQueue(new ArrayList<>());
	}

	@Provides
	@PlayerServiceScope
	public PlayerQueueStorage providePlayerQueueStorage(Context context, Gson gson) {
		return new PlayerQueueStorage(context, gson);
	}

	@Provides
	@PlayerServiceScope
	public PlayerPrefs providePlayerPrefs(AdvancedSharedPrefs basePrefs) {
		return new PlayerPrefs(basePrefs);
	}

	@Provides
	@PlayerServiceScope
	public AudioFocusManager provideAudioFocusManager(Context context, Player player) {
		return new AudioFocusManager(context, player);
	}

	@Provides
	@PlayerServiceScope
	public MediaSessionManager provideMediaSessionManager(Context context, Player player) {
		return new MediaSessionManager(context, player);
	}

	@Provides
	@PlayerServiceScope
	public OpenSLMediaPlayerFactory provideOpenSLMediaPlayerFactory(Context context) {
		return new OpenSLMediaPlayerFactory(context) {
			@Override
			protected int getMediaPlayerOptions() {
				return 0;
			}
		};
	}

	@Provides
	@PlayerServiceScope
	public OpenSLAudioEngine provideOpenSLAudioEngine(OpenSLMediaPlayerFactory factory) {
		return new OpenSLAudioEngine(factory.createMediaPlayer());
	}
}
