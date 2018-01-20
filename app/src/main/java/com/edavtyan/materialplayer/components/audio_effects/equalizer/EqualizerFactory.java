package com.edavtyan.materialplayer.components.audio_effects.equalizer;

import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.PresetsPrefs;
import com.edavtyan.materialplayer.components.player.PlayerServiceScope;
import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
public class EqualizerFactory {
	@Provides
	@PlayerServiceScope
	public Equalizer provideEqualizer(
			EqualizerBase base, EqualizerPrefs prefs, PresetsPrefs presetsPrefs) {
		return new StandardEqualizer(base, prefs, presetsPrefs);
	}

	@Provides
	@PlayerServiceScope
	public EqualizerBase provideEqualizerBase(int sessionId) {
		return new StandardEqualizerBase(new android.media.audiofx.Equalizer(0, sessionId));
	}

	@Provides
	@PlayerServiceScope
	public EqualizerPrefs providePrefs(AdvancedSharedPrefs prefs) {
		return new EqualizerPrefs(prefs);
	}

	@Provides
	@PlayerServiceScope
	public PresetsPrefs providePresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		return new PresetsPrefs(prefs);
	}
}
