package com.edavtyan.materialplayer.components.player;

import android.content.Context;

import com.edavtyan.materialplayer.components.player.engines.AudioEngine;
import com.edavtyan.materialplayer.components.player.engines.ExtendedAudioEngine;
import com.edavtyan.materialplayer.components.player.managers.AudioFocusManager;
import com.edavtyan.materialplayer.components.player.managers.MediaSessionManager;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import net.protyposis.android.mediaplayer.MediaPlayer;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerFactory {
	private final Context context;

	public PlayerFactory(Context context) {
		this.context = context;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public int provideSessionId(Player player) {
		return player.getSessionId();
	}

	@Provides
	@Singleton
	public Player providePlayer(AudioEngine audioEngine, PlayerQueue queue, PlayerPrefs prefs) {
		return new Player(audioEngine, queue, prefs);
	}

	@Provides
	@Singleton
	public AudioEngine provideAudioEngine() {
		return new ExtendedAudioEngine(new MediaPlayer());
	}

	@Provides
	@Singleton
	public PlayerQueue providePlayerQueue() {
		return new PlayerQueue(new ArrayList<>());
	}

	@Provides
	@Singleton
	public PlayerPrefs providePlayerPrefs(AdvancedSharedPrefs basePrefs) {
		return new PlayerPrefs(basePrefs);
	}

	@Provides
	@Singleton
	public AudioFocusManager provideAudioFocusManager(Context context, Player player) {
		return new AudioFocusManager(context, player);
	}

	@Provides
	@Singleton
	public MediaSessionManager provideMediaSessionManager(Context context, Player player) {
		return new MediaSessionManager(context, player);
	}
}
