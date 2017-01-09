package com.edavtyan.materialplayer.testlib.asertions;

import static org.assertj.core.api.Fail.fail;

public class NoNpeAssert {
	public static void assertToNotThrowNPE(Runnable runnable) {
		try {
			runnable.run();
		} catch (NullPointerException e) {
			fail("Expected to not throw NPE", e);
		}
	}
}
