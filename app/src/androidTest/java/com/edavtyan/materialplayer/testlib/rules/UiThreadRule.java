package com.edavtyan.materialplayer.testlib.rules;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class UiThreadRule implements TestRule {
	private Instrumentation instrumentation;

	public UiThreadRule() {
		this.instrumentation = InstrumentationRegistry.getInstrumentation();
	}

	@Override public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override public void evaluate() throws Throwable {
				instrumentation.runOnMainSync(() -> {
					try {
						base.evaluate();
					} catch (Throwable throwable) {
						throwable.printStackTrace();
					}
				});
			}
		};
	}
}
