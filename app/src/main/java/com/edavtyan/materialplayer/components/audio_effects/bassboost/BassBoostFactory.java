package com.edavtyan.materialplayer.components.audio_effects.bassboost;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BassBoostFactory {
	@Provides
	@Singleton
	public BassBoost provideBassBoost(BassBoostPrefs prefs, int sessionId) {
		return new StandardBassBoost(
				new android.media.audiofx.BassBoost(0, sessionId),
				prefs);
	}

	@Provides
	@Singleton
	public BassBoostPrefs provideBassBoostPrefs(AdvancedSharedPrefs prefs) {
		return new BassBoostPrefs(prefs);
	}
}
