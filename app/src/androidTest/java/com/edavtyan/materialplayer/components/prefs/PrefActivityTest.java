package com.edavtyan.materialplayer.components.prefs;

import android.annotation.SuppressLint;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressLint("StaticFieldLeak")
public class PrefActivityTest extends ActivityTest {
	private static PrefActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			activity = startActivity(PrefActivity.class);
		}
	}

	@Test
	public void getLayoutId_prefActivityLayout() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_pref);
	}

	@Test
	public void getToolbarTitleStringId_prefTitle() {
		assertThat(activity.getToolbarTitleStringId()).isEqualTo(R.string.pref_title);
	}
}
