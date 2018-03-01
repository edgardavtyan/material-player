package com.edavtyan.materialplayer.ui.now_playing;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;

import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class NowPlayingActivityTest extends ActivityTest {
	private static NowPlayingPresenter presenter;
	private static NowPlayingArt art;
	private static NowPlayingControls controls;
	private static NowPlayingInfo info;
	private static NowPlayingSeekbar seekbar;
	private static Navigator navigator;

	public static class TestNowPlayingActivity extends NowPlayingActivity {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.presenter = NowPlayingActivityTest.presenter;
			this.art = NowPlayingActivityTest.art;
			this.controls = NowPlayingActivityTest.controls;
			this.info = NowPlayingActivityTest.info;
			this.seekbar = NowPlayingActivityTest.seekbar;
			this.navigator = NowPlayingActivityTest.navigator;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected NowPlayingDIComponent getComponent() {
			return mock(NowPlayingDIComponent.class);
		}
	}

	@Rule
	public final ActivityTestRule<TestNowPlayingActivity> activityRule
			= new ActivityTestRule<>(TestNowPlayingActivity.class, false, false);

	private TestNowPlayingActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(NowPlayingPresenter.class);
		art = mock(NowPlayingArt.class);
		controls = mock(NowPlayingControls.class);
		info = mock(NowPlayingInfo.class);
		seekbar = mock(NowPlayingSeekbar.class);
		navigator = mock(Navigator.class);

		activity = activityRule.launchActivity(null);
	}

	@Test
	public void getArt_returnArtFromFactory() {
		assertThat(activity.getArt()).isSameAs(art);
	}

	@Test
	public void getControls_returnControlsFromFactory() {
		assertThat(activity.getControls()).isSameAs(controls);
	}

	@Test
	public void getInfo_returnInfoFromFactory() {
		assertThat(activity.getInfo()).isSameAs(info);
	}

	@Test
	public void getSeekbar_returnSeekbarFromFactory() {
		assertThat(activity.getSeekbar()).isSameAs(seekbar);
	}

	@Test
	public void gotoPlaylistScreen_callNavigator() {
		activity.gotoPlaylistScreen();
		verify(navigator).gotoNowPlayingQueue(activity);
	}
}
