package com.edavtyan.materialplayer.lib.replaygain;

import android.content.Context;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;
import com.edavtyan.materialplayer.service.PlayerServiceScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ReplayGainDIModule {
	@Provides
	@PlayerServiceScope
	public ReplayGainManager provideReplayGainManager(
			@Nullable Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainPrefs prefs) {
		return new ReplayGainManager(amplifier, rgReader, prefs);
	}

	@Provides
	@PlayerServiceScope
	public ReplayGainReader provideReplayGainReader() {
		return new ReplayGainReader();
	}

	@Provides
	@PlayerServiceScope
	public ReplayGainPrefs provideReplayGainPrefs(Context context, AdvancedSharedPrefs prefs) {
		return new ReplayGainPrefs(context, prefs);
	}
}
