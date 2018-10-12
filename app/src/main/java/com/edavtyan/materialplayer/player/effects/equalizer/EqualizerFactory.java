package com.edavtyan.materialplayer.player.effects.equalizer;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.service.PlayerServiceScope;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class EqualizerFactory {
	@Provides
	@PlayerServiceScope
	public Equalizer provideEqualizer(
			@Nullable OpenSLEqualizerBase base, EqualizerPrefs prefs, PresetsPrefs presetsPrefs) {
		return new StandardEqualizer(base, prefs, presetsPrefs);
	}

	@Nullable
	@Provides
	@PlayerServiceScope
	public EqualizerBase provideEqualizerBase(int sessionId) {
		try {
			return new StandardEqualizerBase(new android.media.audiofx.Equalizer(0, sessionId));
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Provides
	@PlayerServiceScope
	public OpenSLEqualizerBase provideOpenSLEqualizerBase(OpenSLMediaPlayerFactory factory) {
		return new OpenSLEqualizerBase(factory.createHQEqualizer());
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
