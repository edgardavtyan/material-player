package com.edavtyan.materialplayer.lib.replaygain;

import android.support.annotation.Nullable;

import lombok.Getter;

public class ReplayGainData {
	private final @Nullable @Getter Double trackRG;
	private final @Nullable @Getter Double albumRG;

	public ReplayGainData(@Nullable Double trackRG, @Nullable Double albumRG) {
		this.trackRG = trackRG;
		this.albumRG = albumRG;
	}
}
