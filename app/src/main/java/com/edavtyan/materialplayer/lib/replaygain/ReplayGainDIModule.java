package com.edavtyan.materialplayer.lib.replaygain;

import android.content.Context;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;
import com.edavtyan.materialplayer.service.PlayerServiceScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ReplayGainDIModule {
	@Provides
	@PlayerServiceScope
	public ReplayGainManager provideReplayGainManager(
			Player player,
			@Nullable Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainEnabledPref rgEnabledPref,
			ReplayGainPreampPref rgPreampPref) {
		return new ReplayGainManager(player, amplifier, rgReader, rgEnabledPref, rgPreampPref);
	}

	@Provides
	@PlayerServiceScope
	public ReplayGainReader provideReplayGainReader() {
		return new ReplayGainReader();
	}

	@Provides
	@PlayerServiceScope
	public ReplayGainEnabledPref provideReplayGainEnabledPref(Context context, AdvancedSharedPrefs prefs) {
		return new ReplayGainEnabledPref(context, prefs);
	}

	@Provides
	@PlayerServiceScope
	public ReplayGainPreampPref provideReplayGainPreampPref(Context context, AdvancedSharedPrefs prefs) {
		return new ReplayGainPreampPref(context, prefs);
	}
}
