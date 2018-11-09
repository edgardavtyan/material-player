package com.edavtyan.materialplayer.player.effects.equalizer;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.service.PlayerServiceScope;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class EqualizerFactory {
	@Provides
	@Named("opensl")
	@PlayerServiceScope
	public Equalizer provideStandardEqualizer(
			@Nullable OpenSLEqualizerBase base,
			EqualizerPrefs prefs,
			StandardPresetsPrefs presetsPrefs) {
		return new StandardEqualizer(base, prefs, presetsPrefs);
	}

	@Provides
	@Named("standard")
	@PlayerServiceScope
	public Equalizer provideOpenSLEqualizer(
			@Nullable StandardEqualizerBase base,
			EqualizerPrefs prefs,
			OpenSLPresetsPrefs presetsPrefs) {
		return new StandardEqualizer(base, prefs, presetsPrefs);
	}

	@Nullable
	@Provides
	@PlayerServiceScope
	public StandardEqualizerBase provideEqualizerBase(int sessionId) {
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
	public StandardPresetsPrefs provideStandardPresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		return new StandardPresetsPrefs(prefs);
	}

	@Provides
	@PlayerServiceScope
	public OpenSLPresetsPrefs provideOpenSLPresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		return new OpenSLPresetsPrefs(prefs);
	}
}
