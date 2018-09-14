package com.edavtyan.materialplayer.lib.replaygain;

public class ReplayGainNotFoundException extends Throwable {
	public ReplayGainNotFoundException() {
		super("Track does not contain ReplayGain info");
	}
}
