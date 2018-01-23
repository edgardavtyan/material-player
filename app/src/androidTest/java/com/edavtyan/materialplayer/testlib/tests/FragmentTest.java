package com.edavtyan.materialplayer.testlib.tests;

import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.testlib.TestActivity;

public class FragmentTest extends BaseTest {
	public void initFragment(Fragment fragment) {
		TestActivity testActivity = startActivity(TestActivity.class);
		testActivity
				.getSupportFragmentManager()
				.beginTransaction()
				.add(android.R.id.content, fragment)
				.commit();
		instrumentation.waitForIdleSync();
	}
}
