package com.edavtyan.materialplayer.player.effects.surround;

import com.edavtyan.materialplayer.service.PlayerServiceScope;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
public class SurroundFactory {
	@Provides
	@PlayerServiceScope
	public Surround provideSurround(SurroundPrefs prefs, int sessionId) {
		return new StandardSurround(
				new android.media.audiofx.Virtualizer(0, sessionId),
				prefs);
	}

	@Provides
	@PlayerServiceScope
	public SurroundPrefs provideSurroundPrefs(AdvancedSharedPrefs prefs) {
		return new SurroundPrefs(prefs);
	}
}
