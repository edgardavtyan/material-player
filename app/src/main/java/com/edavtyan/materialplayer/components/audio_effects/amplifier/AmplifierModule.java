package com.edavtyan.materialplayer.components.audio_effects.amplifier;

import android.annotation.TargetApi;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@TargetApi(Build.VERSION_CODES.KITKAT)
public class AmplifierModule {
	@Provides
	@Singleton
	public Amplifier provideAmplifier(AmplifierPrefs prefs, int sessionId) {
		return new StandardAmplifier(new LoudnessEnhancer(sessionId), prefs);
	}

	@Provides
	@Singleton
	public AmplifierPrefs provideAmplifierPrefs(AdvancedSharedPrefs prefs) {
		return new AmplifierPrefs(prefs);
	}
}
