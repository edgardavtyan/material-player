package com.edavtyan.materialplayer.testlib.tests;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryTest extends BaseTest {
	protected <T> void testFactoryMethod(Callable<T> callable) throws Exception {
		AtomicReference<Exception> exceptionRef = new AtomicReference<>();

		runOnUiThread(() -> {
			try {
				assertThat(callable.call()).isSameAs(callable.call());
			} catch (Exception e) {
				exceptionRef.set(e);
			}
		});

		Exception exception = exceptionRef.get();
		if (exception != null) throw exception;
	}
}
