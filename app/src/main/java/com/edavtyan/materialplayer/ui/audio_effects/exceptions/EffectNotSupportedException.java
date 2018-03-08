package com.edavtyan.materialplayer.ui.audio_effects.exceptions;

public class EffectNotSupportedException extends RuntimeException {
	public EffectNotSupportedException(String effect) {
		super("Effect " + effect + " not supported");
	}
}
