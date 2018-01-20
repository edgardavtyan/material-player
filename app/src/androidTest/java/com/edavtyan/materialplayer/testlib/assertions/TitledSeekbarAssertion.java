package com.edavtyan.materialplayer.testlib.assertions;

import com.edavtyan.materialplayer.screens.audio_effects.views.TitledSeekbar;

import org.assertj.core.api.AbstractAssert;

public class TitledSeekbarAssertion extends AbstractAssert<TitledSeekbarAssertion, TitledSeekbar> {
	public TitledSeekbarAssertion(TitledSeekbar actual) {
		super(actual, TitledSeekbarAssertion.class);
	}

	@SuppressWarnings("UnusedReturnValue")
	public TitledSeekbarAssertion hasProgress(int progress) {
		String errorMessage = "\nExpected progress to be\n<%s>\nbut was\n<%s>";
		if (actual.getProgress() != progress)
			failWithMessage(errorMessage, progress, actual.getProgress());
		return this;
	}

	public TitledSeekbarAssertion hasMax(int max) {
		String errorMessage = "\nExpected max to be\n<%s>\nbut was\n<%s>";
		if (actual.getMax() != max)
			failWithMessage(errorMessage, max, actual.getMax());
		return this;
	}
}
