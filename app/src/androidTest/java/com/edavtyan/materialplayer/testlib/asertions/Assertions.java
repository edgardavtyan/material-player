package com.edavtyan.materialplayer.testlib.asertions;

import android.content.Intent;

public final class Assertions {
	public static IntentAssert assertThat(Intent intent) {
		return new IntentAssert(intent);
	}
}
