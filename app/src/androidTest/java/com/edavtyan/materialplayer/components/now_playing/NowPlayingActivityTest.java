package com.edavtyan.materialplayer.components.now_playing;

import android.annotation.SuppressLint;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class NowPlayingActivityTest extends ActivityTest {
	private static NowPlayingActivity activity;
	private static NowPlayingFactory factory;
	private static NowPlayingMvp.Presenter presenter;
	private static NowPlayingMvp.View.Art art;
	private static NowPlayingMvp.View.Controls controls;
	private static NowPlayingMvp.View.Info info;
	private static NowPlayingMvp.View.Seekbar seekbar;
	private static Navigator navigator;

	public static class TestNowPlayingActivity extends NowPlayingActivity {
		@Override
		public NowPlayingFactory getFactory() {
			return factory;
		}
	}

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			presenter = mock(NowPlayingMvp.Presenter.class);
			art = mock(NowPlayingMvp.View.Art.class);
			controls = mock(NowPlayingMvp.View.Controls.class);
			info = mock(NowPlayingMvp.View.Info.class);
			seekbar = mock(NowPlayingMvp.View.Seekbar.class);
			navigator = mock(Navigator.class);

			factory = mock(NowPlayingFactory.class);
			when(factory.getPresenter()).thenReturn(presenter);
			when(factory.getArt()).thenReturn(art);
			when(factory.getControls()).thenReturn(controls);
			when(factory.getInfo()).thenReturn(info);
			when(factory.getSeekbar()).thenReturn(seekbar);
			when(factory.getNavigator()).thenReturn(navigator);

			activity = spy(startActivity(TestNowPlayingActivity.class));
			doNothing().when(activity).baseOnDestroy();
		}
	}

	@Test
	public void onCreate_bindPresenter() {
		verify(presenter).bind();
	}

	@Test
	public void onDestroy_unbindPresenter() {
		activity.onDestroy();
		verify(presenter).unbind();
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
	public void goToPlaylistScreen_callNavigator() {
		activity.gotoPlaylistScreen();
		verify(navigator).gotoNowPlayingQueue();
	}
}
