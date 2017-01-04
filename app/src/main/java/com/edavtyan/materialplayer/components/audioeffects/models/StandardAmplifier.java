package com.edavtyan.materialplayer.components.audioeffects.models;

import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class StandardAmplifier implements Amplifier {
	private final LoudnessEnhancer amplifier;
	private final AmplifierPrefs prefs;

	public StandardAmplifier(LoudnessEnhancer amplifier, AmplifierPrefs prefs) {
		this.amplifier = amplifier;
		this.amplifier.setEnabled(true);
		this.prefs = prefs;

		setGain(prefs.getGain());
	}

	@Override
	public int getGain() {
		return (int) amplifier.getTargetGain() / 100;
	}

	@Override
	public void setGain(int gain) {
		amplifier.setTargetGain(gain * 100);
	}

	@Override
	public int getMaxGain() {
		return 15;
	}

	@Override
	public void saveSettings() {
		prefs.save(getGain());
	}
}
