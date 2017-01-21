package com.edavtyan.materialplayer.testlib.assertions;

import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;

import org.assertj.core.api.AbstractAssert;

public class EqualizerBandAssert extends AbstractAssert<EqualizerBandAssert, EqualizerBandView> {
	public EqualizerBandAssert(EqualizerBandView actual) {
		super(actual, EqualizerBandAssert.class);
	}

	public EqualizerBandAssert hasGainLimit(int gainLimit) {
		String errorMessage = "\nExpected gain limit to be\n<%s>\nbut was\n<%s>\n";
		if (gainLimit != actual.getGainLimit())
			failWithMessage(errorMessage, gainLimit, actual.getGainLimit());
		return this;
	}

	@SuppressWarnings("UnusedReturnValue")
	public EqualizerBandAssert hasGain(int gain) {
		String errorMessage = "\nExpected gain to be\n<%s>\nbut was\n<%s>\n";
		if (gain != actual.getGain())
			failWithMessage(errorMessage, gain, actual.getGain());
		return this;
	}

	public EqualizerBandAssert hasFrequency(int frequency) {
		String errorMessage = "\nExpected frequency to be\n<%s>\nbut was\n<%s>\n";
		if (frequency != actual.getFrequency())
			failWithMessage(errorMessage, frequency, actual.getFrequency());
		return this;
	}
}
