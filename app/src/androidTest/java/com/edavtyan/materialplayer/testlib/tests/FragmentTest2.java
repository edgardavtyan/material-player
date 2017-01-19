package com.edavtyan.materialplayer.testlib.tests;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.testlib.TestActivity;

public class FragmentTest2 extends BaseTest {
	public void initFragment(Fragment fragment) {
		TestActivity testActivity = startActivity(TestActivity.class);
		testActivity
				.getSupportFragmentManager()
				.beginTransaction()
				.add(android.R.id.content, fragment)
				.commit();
		instrumentation.waitForIdleSync();
	}

	@SuppressWarnings("unchecked")
	private <T> T startActivity(Class activityClass) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClass(context, activityClass);
		return (T) instrumentation.startActivitySync(intent);
	}
}
