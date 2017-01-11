package com.edavtyan.materialplayer.testlib.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

public class ActivityTest extends BaseTest {
	protected Instrumentation instrumentation;

	@Override
	public void beforeEach() {
		super.beforeEach();
		instrumentation = InstrumentationRegistry.getInstrumentation();
	}

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

	protected void runOnUiThread(Runnable runnable) {
		instrumentation.runOnMainSync(runnable);
	}
}
