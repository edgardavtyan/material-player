package com.edavtyan.materialplayer.components.audio_effects.bassboost;

import com.edavtyan.materialplayer.components.player.PlayerServiceScope;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BassBoostFactory {
	@Provides
	@PlayerServiceScope
	public BassBoost provideBassBoost(BassBoostPrefs prefs, int sessionId) {
		return new StandardBassBoost(
				new android.media.audiofx.BassBoost(0, sessionId),
				prefs);
	}

	@Provides
	@PlayerServiceScope
	public BassBoostPrefs provideBassBoostPrefs(AdvancedSharedPrefs prefs) {
		return new BassBoostPrefs(prefs);
	}
}
