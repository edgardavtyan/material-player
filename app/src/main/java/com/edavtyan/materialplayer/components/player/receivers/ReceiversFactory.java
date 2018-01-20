package com.edavtyan.materialplayer.components.player.receivers;

import android.content.Context;

import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.components.player.PlayerServiceScope;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceiversFactory {
	@Provides
	@PlayerServiceScope
	public AudioBecomingNoisyReceiver provideAudioBecomingNoisyReceiver(Player player) {
		return new AudioBecomingNoisyReceiver(player);
	}

	@Provides
	@PlayerServiceScope
	public CloseReceiver provideCloseReceiver() {
		return new CloseReceiver();
	}

	@Provides
	@PlayerServiceScope
	public HeadphonesConnectedReceiver provideHeadphonesConnectedReceiver(
			Player player, PlayOnHeadsetPluggedPref playerOnHeadsetPluggedPref) {
		return new HeadphonesConnectedReceiver(player, playerOnHeadsetPluggedPref);
	}

	@Provides
	@PlayerServiceScope
	public MediaButtonReceiver provideMediaButtonReceiver(Player player) {
		return new MediaButtonReceiver(player);
	}

	@Provides
	@PlayerServiceScope
	public PlayPauseReceiver providePlayPauseReceiver(Player player) {
		return new PlayPauseReceiver(player);
	}

	@Provides
	@PlayerServiceScope
	public SkipToNextReceiver provideSkipToNextReceiver(Player player) {
		return new SkipToNextReceiver(player);
	}

	@Provides
	@PlayerServiceScope
	public SkipToPreviousReceiver provideSkipToPreviousReceiver(Player player) {
		return new SkipToPreviousReceiver(player);
	}

	@Provides
	@PlayerServiceScope
	public PlayOnHeadsetPluggedPref providePlayOnHeadsetPluggedPref(
			Context context, AdvancedSharedPrefs prefs) {
		return new PlayOnHeadsetPluggedPref(context, prefs);
	}
}
