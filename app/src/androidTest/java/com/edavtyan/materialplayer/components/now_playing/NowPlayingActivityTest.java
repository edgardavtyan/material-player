package com.edavtyan.materialplayer.components.now_playing;

import android.annotation.SuppressLint;
import android.support.test.rule.ActivityTestRule;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class NowPlayingActivityTest extends ActivityTest {
	@Rule
	public final ActivityTestRule<NowPlayingActivity> activityRule
			= new ActivityTestRule<>(NowPlayingActivity.class, false, false);

	private NowPlayingActivity activity;
	private NowPlayingPresenter presenter;
	private NowPlayingArt art;
	private NowPlayingControls controls;
	private NowPlayingInfo info;
	private NowPlayingSeekbar seekbar;
	private Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(NowPlayingPresenter.class);
		art = mock(NowPlayingArt.class);
		controls = mock(NowPlayingControls.class);
		info = mock(NowPlayingInfo.class);
		seekbar = mock(NowPlayingSeekbar.class);
		navigator = mock(Navigator.class);

		NowPlayingFactory factory = mock(NowPlayingFactory.class);
		when(factory.getPresenter()).thenReturn(presenter);
		when(factory.getArt()).thenReturn(art);
		when(factory.getControls()).thenReturn(controls);
		when(factory.getInfo()).thenReturn(info);
		when(factory.getSeekbar()).thenReturn(seekbar);
		when(factory.getNavigator()).thenReturn(navigator);

		app.setNowPlayingFactory(factory);

		activity = startActivity(NowPlayingActivity.class);
	}

	@Test
	public void getToolbarTitleStringId_returnCorrectStringId() {
		assertThat(activity.getToolbarTitleStringId()).isEqualTo(R.string.nowplaying_toolbar_title);
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
