package com.edavtyan.materialplayer.app.music.effects.equalizer;

import lombok.Data;

@Data
public class EqualizerPreset {
	private int index;
	private String name;
	private int[] gains;
}
