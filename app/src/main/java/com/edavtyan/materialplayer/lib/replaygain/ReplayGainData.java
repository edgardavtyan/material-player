package com.edavtyan.materialplayer.lib.replaygain;

import android.support.annotation.Nullable;

import lombok.Getter;

public class ReplayGainData {
	private final @Nullable @Getter Double trackRG;
	private final @Nullable @Getter Double albumRG;
	private final @Nullable @Getter Double trackPeak;
	private final @Nullable @Getter Double albumPeak;

	public ReplayGainData(
			@Nullable Double trackRG,
			@Nullable Double albumRG,
			@Nullable Double trackPeak,
			@Nullable Double albumPeak) {
		this.trackRG = trackRG;
		this.albumRG = albumRG;
		this.trackPeak = trackPeak;
		this.albumPeak = albumPeak;
	}
}
