package com.edavtyan.materialplayer.components.player.receivers;

import android.content.Context;

import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceiversModule {
	@Provides
	@Singleton
	public AudioBecomingNoisyReceiver provideAudioBecomingNoisyReceiver(Player player) {
		return new AudioBecomingNoisyReceiver(player);
	}

	@Provides
	@Singleton
	public CloseReceiver provideCloseReceiver() {
		return new CloseReceiver();
	}

	@Provides
	@Singleton
	public HeadphonesConnectedReceiver provideHeadphonesConnectedReceiver(
			Player player, PlayOnHeadsetPluggedPref playerOnHeadsetPluggeedPref) {
		return new HeadphonesConnectedReceiver(player, playerOnHeadsetPluggeedPref);
	}

	@Provides
	@Singleton
	public MediaButtonReceiver provideMediaButtonReceiver(Player player) {
		return new MediaButtonReceiver(player);
	}

	@Provides
	@Singleton
	public PlayPauseReceiver providePlayPauseReceiver(Player player) {
		return new PlayPauseReceiver(player);
	}

	@Provides
	@Singleton
	public SkipToNextReceiver provideSkipToNextReceiver(Player player) {
		return new SkipToNextReceiver(player);
	}

	@Provides
	@Singleton
	public SkipToPreviousReceiver provideSkipToPreviousReceiver(Player player) {
		return new SkipToPreviousReceiver(player);
	}

	@Provides
	@Singleton
	public PlayOnHeadsetPluggedPref providePlayOnHeadsetPluggedPref(
			Context context, AdvancedSharedPrefs prefs) {
		return new PlayOnHeadsetPluggedPref(context, prefs);
	}
}
