package com.edavtyan.materialplayer.components.audioeffects.equalizer.presets;

public class PresetNameAlreadyExists extends Exception {
	public PresetNameAlreadyExists(String presetName) {
		super("Preset with name " + presetName + " already exists");
	}
}
