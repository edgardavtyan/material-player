package com.edavtyan.materialplayer.screens.audio_effects.presets;

public class PresetNameAlreadyExists extends Exception {
	public PresetNameAlreadyExists(String presetName) {
		super("Preset with name " + presetName + " already exists");
	}
}
