package com.edavtyan.materialplayer.components.now_playing.models;

import android.annotation.SuppressLint;
import android.support.test.rule.ActivityTestRule;

import com.edavtyan.materialplayer.components.now_playing.DaggerNowPlayingComponent;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingComponent;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingModule;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingPresenter;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public abstract class NowPlayingViewTest extends ActivityTest {
	private static NowPlayingComponent component;

	public static class TestNowPlayingActivity extends NowPlayingActivity {
		@Override
		protected NowPlayingComponent getComponent() {
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

		NowPlayingModule mockNowPlayingModule = mock(NowPlayingModule.class, RETURNS_MOCKS);
		when(mockNowPlayingModule.providePresenter(any(), any())).thenReturn(presenter);
		component = DaggerNowPlayingComponent
				.builder()
				.nowPlayingModule(mockNowPlayingModule)
				.build();

		activity = activityRule.launchActivity(null);
	}
}
