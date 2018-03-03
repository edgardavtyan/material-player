package com.edavtyan.materialplayer.testlib.tests;

import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.testlib.TestActivity;

public class FragmentTest extends BaseTest {
	private TestActivity activity;

	public void initFragment(Fragment fragment) {
		activity = startActivity(TestActivity.class);
		activity.getSupportFragmentManager()
				.beginTransaction()
				.add(android.R.id.content, fragment)
				.commit();
		instrumentation.waitForIdleSync();
	}

	public TestActivity getActivity() {
		return activity;
	}
}
