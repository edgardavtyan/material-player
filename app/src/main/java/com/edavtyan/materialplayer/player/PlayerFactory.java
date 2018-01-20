package com.edavtyan.materialplayer.player;

import android.content.Context;

import com.edavtyan.materialplayer.player.engines.AudioEngine;
import com.edavtyan.materialplayer.player.engines.ExtendedAudioEngine;
import com.edavtyan.materialplayer.player.managers.AudioFocusManager;
import com.edavtyan.materialplayer.player.managers.MediaSessionManager;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.service.PlayerServiceScope;

import net.protyposis.android.mediaplayer.MediaPlayer;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerFactory {
	@Provides
	@PlayerServiceScope
	public int provideSessionId(Player player) {
		return player.getSessionId();
	}

	@Provides
	@PlayerServiceScope
	public Player providePlayer(AudioEngine audioEngine, PlayerQueue queue, PlayerPrefs prefs) {
		return new Player(audioEngine, queue, prefs);
	}

	@Provides
	@PlayerServiceScope
	public AudioEngine provideAudioEngine() {
		return new ExtendedAudioEngine(new MediaPlayer());
	}

	@Provides
	@PlayerServiceScope
	public PlayerQueue providePlayerQueue() {
		return new PlayerQueue(new ArrayList<>());
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
}
