package com.edavtyan.materialplayer.testlib.asertions;

import org.assertj.core.api.AbstractAssert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class ConstructorAssert<T> extends AbstractAssert<ConstructorAssert<T>, Constructor<T>> {
	public ConstructorAssert(Constructor<T> actual, Class<ConstructorAssert> selfType) {
		super(actual, selfType);
	}

	public static <T> ConstructorAssert<T> assertThatConstructor(Constructor<T> actual) {
		return new ConstructorAssert<>(actual, ConstructorAssert.class);
	}

	@SuppressWarnings("UnusedReturnValue")
	public ConstructorAssert isPrivate() {
		isNotNull();

		try {
			actual.setAccessible(true);
			actual.newInstance();

			if (!Modifier.isPrivate(actual.getModifiers())) {
				failWithMessage("Constructor of %s is not private", actual.getName());
			}
		} catch (Exception e) {
			failWithMessage(
					"Exception occurred\nmessage: %s\ntrace:5s",
					e.getMessage(),
					e.getStackTrace());
		}

		return this;
	}
}
