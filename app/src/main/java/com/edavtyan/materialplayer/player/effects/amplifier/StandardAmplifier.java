package com.edavtyan.materialplayer.player.effects.amplifier;

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
		return milliToDeci((int) amplifier.getTargetGain());
	}

	@Override
	public void setGain(int gain) {
		amplifier.setTargetGain(deciToMilli(gain));
	}

	@Override
	public int getMaxGain() {
		return 15;
	}

	@Override
	public void saveSettings() {
		prefs.save(getGain());
	}

	private int milliToDeci(int value) {
		return value / 100;
	}

	private int deciToMilli(int value) {
		return value * 100;
	}
}
