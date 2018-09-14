package com.edavtyan.materialplayer.lib.replaygain;

import com.ed.libsutils.utils.Logger;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;

public class ReplayGainManager {
	private final Amplifier amplifier;
	private final ReplayGainReader rgReader;

	public ReplayGainManager(Amplifier amplifier, ReplayGainReader rgReader) {
		this.amplifier = amplifier;
		this.rgReader = rgReader;
	}

	public void apply(String path) {
		try {
			amplifier.setGain(rgReader.read(path));
			Logger.d(this, "ReplayGain: applied ", rgReader.read(path));
		} catch (ReplayGainNotFoundException e) {
			Logger.d(this, "ReplayGain: not found");
			amplifier.setGain(0);
		}
	}
}
