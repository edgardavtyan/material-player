package com.edavtyan.materialplayer.components.audio_effects.amplifier;

import android.annotation.TargetApi;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@TargetApi(Build.VERSION_CODES.KITKAT)
public class AmplifierFactory {
	@Provides
	@Singleton
	@Nullable
	public Amplifier provideAmplifier(AmplifierPrefs prefs, int sessionId) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			return new StandardAmplifier(new LoudnessEnhancer(sessionId), prefs);
		} else {
			return null;
		}
	}

	@Provides
	@Singleton
	public AmplifierPrefs provideAmplifierPrefs(AdvancedSharedPrefs prefs) {
		return new AmplifierPrefs(prefs);
	}
}
