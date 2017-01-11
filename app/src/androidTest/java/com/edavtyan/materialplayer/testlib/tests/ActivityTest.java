package com.edavtyan.materialplayer.testlib.tests;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

public class ActivityTest extends BaseTest {
	@SuppressWarnings("unchecked")
	protected <T> T startActivity(Intent intent) {
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return (T) InstrumentationRegistry.getInstrumentation().startActivitySync(intent);
	}

	@SuppressWarnings("unchecked")
	protected <T> T startActivity(Class<? extends Activity> activityClass) {
		Intent intent = new Intent(context, activityClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return (T) InstrumentationRegistry.getInstrumentation().startActivitySync(intent);
	}
}
