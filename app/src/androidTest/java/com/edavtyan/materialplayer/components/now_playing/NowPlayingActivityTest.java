package com.edavtyan.materialplayer.components.now_playing;

import android.annotation.SuppressLint;
import android.content.Intent;

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
			when(factory.providePresenter()).thenReturn(presenter);
			when(factory.provideArt()).thenReturn(art);
			when(factory.provideControls()).thenReturn(controls);
			when(factory.provideInfo()).thenReturn(info);
			when(factory.provideSeekbar()).thenReturn(seekbar);
			when(factory.provideNavigator()).thenReturn(navigator);

			activity = spy(startActivity(new Intent(context, TestNowPlayingActivity.class)));
			doNothing().when(activity).baseOnDestroy();
		}
	}

	@Test
	public void bind_presenter_when_created() {
		verify(presenter).bind();
	}

	@Test
	public void unbind_presenter_when_destroyed() {
		activity.onDestroy();
		verify(presenter).unbind();
	}

	@Test
	public void return_correct_toolbar_title() {
		assertThat(activity.getToolbarTitleStringId()).isEqualTo(R.string.nowplaying_toolbar_title);
	}

	@Test
	public void return_art() {
		assertThat(activity.getArt()).isSameAs(art);
	}

	@Test
	public void return_controls() {
		assertThat(activity.getControls()).isSameAs(controls);
	}

	@Test
	public void return_info() {
		assertThat(activity.getInfo()).isSameAs(info);
	}

	@Test
	public void return_seekbar() {
		assertThat(activity.getSeekbar()).isSameAs(seekbar);
	}

	@Test
	public void navigate_to_playlist_screen() {
		activity.gotoPlaylistScreen();
		verify(navigator).gotoNowPlayingQueue();
	}
}
