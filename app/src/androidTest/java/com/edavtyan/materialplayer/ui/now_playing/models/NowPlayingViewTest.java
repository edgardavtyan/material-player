package com.edavtyan.materialplayer.ui.now_playing.models;

import android.annotation.SuppressLint;
import android.support.test.rule.ActivityTestRule;

import com.edavtyan.materialplayer.testlib.tests.ActivityTest;
import com.edavtyan.materialplayer.ui.now_playing.DaggerNowPlayingDIComponent;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingDIComponent;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingDIModule;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingPresenter;

import org.junit.Rule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public abstract class NowPlayingViewTest extends ActivityTest {
	private static NowPlayingDIComponent component;

	public static class TestNowPlayingActivity extends NowPlayingActivity {
		@Override
		protected NowPlayingDIComponent getComponent() {
			return NowPlayingViewTest.component;
		}
	}

	@Rule
	public final ActivityTestRule<NowPlayingViewTest.TestNowPlayingActivity> activityRule
			= new ActivityTestRule<>(NowPlayingViewTest.TestNowPlayingActivity.class, false, false);

	protected NowPlayingViewTest.TestNowPlayingActivity activity;
	protected NowPlayingPresenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(NowPlayingPresenter.class);

		NowPlayingDIModule mockNowPlayingModule = mock(NowPlayingDIModule.class, RETURNS_MOCKS);
		when(mockNowPlayingModule.providePresenter(any(), any())).thenReturn(presenter);
		component = DaggerNowPlayingDIComponent
				.builder()
				.nowPlayingDIModule(mockNowPlayingModule)
				.build();

		activity = activityRule.launchActivity(null);
	}
}
