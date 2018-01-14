package com.edavtyan.materialplayer.components.main;

import android.annotation.SuppressLint;
import android.support.test.rule.ActivityTestRule;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressLint("StaticFieldLeak")
public class MainActivityTest extends ActivityTest {
	@Rule
	public final ActivityTestRule<MainActivity> activityRule
			= new ActivityTestRule<>(MainActivity.class);

	private MainActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();
		activity = activityRule.getActivity();
	}

	@Test
	public void return_correct_layout_id() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_main);
	}
}
