package com.edavtyan.materialplayer.components.audio_effects.equalizer.presets;

import lombok.Getter;
import lombok.Setter;

public class CustomPreset {
	private @Getter @Setter String name;
	private @Getter @Setter int[] gains;

	public CustomPreset(String name, int[] gains) {
		this.name = name;
		this.gains = gains;
	}
}
