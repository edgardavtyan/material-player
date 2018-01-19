package com.edavtyan.materialplayer.components.audio_effects.equalizer;

import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.PresetsPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EqualizerFactory {
	@Provides
	@Singleton
	public Equalizer provideEqualizer(
			EqualizerBase base, EqualizerPrefs prefs, PresetsPrefs presetsPrefs) {
		return new StandardEqualizer(base, prefs, presetsPrefs);
	}

	@Provides
	@Singleton
	public EqualizerBase provideEqualizerBase(int sessionId) {
		return new StandardEqualizerBase(new android.media.audiofx.Equalizer(0, sessionId));
	}

	@Provides
	@Singleton
	public EqualizerPrefs providePrefs(AdvancedSharedPrefs prefs) {
		return new EqualizerPrefs(prefs);
	}

	@Provides
	@Singleton
	public PresetsPrefs providePresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		return new PresetsPrefs(prefs);
	}
}
