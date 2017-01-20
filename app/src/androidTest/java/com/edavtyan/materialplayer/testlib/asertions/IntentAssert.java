package com.edavtyan.materialplayer.testlib.asertions;

import android.content.Intent;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class IntentAssert extends AbstractAssert<IntentAssert, Intent> {
	public IntentAssert(Intent actual) {
		super(actual, IntentAssert.class);
	}

	public IntentAssert hasClass(Class clazz) {
		isNotNull();

		String expectedClass = clazz.getName();
		String actualClass = actual.getComponent().getClassName();
		assertThat(expectedClass)
				.overridingErrorMessage(
						"Expected intent class to be (%s), but got (%s)",
						expectedClass, actualClass)
				.isEqualTo(actualClass);
		return this;
	}

	public IntentAssert hasExtraString(String extraName, String extraValue) {
		isNotNull();
		assertThat(actual.getStringExtra(extraName))
				.overridingErrorMessage(
						"Expected intent extra (%s) to have value (%s), but got (%s)",
						extraName, extraValue, actual.getStringExtra(extraName))
				.isEqualTo(extraValue);
		return this;
	}

	public IntentAssert hasExtraInt(String extraName, int extraValue) {
		isNotNull();
		assertThat(actual.getIntExtra(extraName, -1))
				.overridingErrorMessage(
						"Expected intent extra (%s) to have value (%s), but got (%s)",
						extraName, extraValue, actual.getStringExtra(extraName))
				.isEqualTo(extraValue);
		return this;
	}
}
