package com.edavtyan.materialplayer.components.audio_effects.surround;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SurroundFactory {
	@Provides
	@Singleton
	public Surround provideSurround(SurroundPrefs prefs, int sessionId) {
		return new StandardSurround(
				new android.media.audiofx.Virtualizer(0, sessionId),
				prefs);
	}

	@Provides
	@Singleton
	public SurroundPrefs provideSurroundPrefs(AdvancedSharedPrefs prefs) {
		return new SurroundPrefs(prefs);
	}
}
