package com.edavtyan.materialplayer.lib.asertions;

import android.content.Intent;

import org.assertj.core.api.AbstractAssert;

public class IntentAssert extends AbstractAssert<IntentAssert, Intent> {
	public IntentAssert(Intent actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static IntentAssert assertThat(Intent actual) {
		return new IntentAssert(actual, IntentAssert.class);
	}

	public IntentAssert classEqualTo(Class clazz) {
		isNotNull();

		String expectedClass = clazz.getName();
		String actualClass = actual.getComponent().getClassName();

		if (!actualClass.equals(expectedClass)) {
			failWithMessage(
					"Expected intent class to be (%s), but got (%s)",
					expectedClass, actualClass);
		}

		return this;
	}

	public IntentAssert hasExtra(String extraName, String extraValue) {
		isNotNull();

		if (!actual.getStringExtra(extraName).equals(extraValue)) {
			failWithMessage(
					"Expected intent extra (%s) to have value (%s), but got (%s)",
					extraName, extraValue, actual.getStringExtra(extraName));
		}

		return this;
	}
}
