package com.edavtyan.materialplayer.components.main;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;

public class MainActivityTest extends ActivityTest {
	private static MainActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			activity = spy(startActivity(MainActivity.class));
		} else {
			reset(activity);
		}
	}

	@Test
	public void return_correct_layout_id() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_main);
	}

	@Test
	public void has_back_icon_disabled() {
		assertThat(activity.isBackIconEnabled()).isFalse();
	}
}
