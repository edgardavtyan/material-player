package com.edavtyan.materialplayer.components.audioeffects.models;

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
