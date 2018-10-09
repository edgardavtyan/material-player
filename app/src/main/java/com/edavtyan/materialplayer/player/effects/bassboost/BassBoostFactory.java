package com.edavtyan.materialplayer.player.effects.bassboost;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.service.PlayerServiceScope;

import dagger.Module;
import dagger.Provides;

@Module
public class BassBoostFactory {
	@Provides
	@PlayerServiceScope
	public BassBoost provideBassBoost(BassBoostPrefs prefs, int sessionId) {
		try {
			return new StandardBassBoost(
					new android.media.audiofx.BassBoost(0, sessionId),
					prefs);
		} catch (RuntimeException e) {
			return new StandardBassBoost(null, prefs);
		}
	}

	@Provides
	@PlayerServiceScope
	public BassBoostPrefs provideBassBoostPrefs(AdvancedSharedPrefs prefs) {
		return new BassBoostPrefs(prefs);
	}
}
