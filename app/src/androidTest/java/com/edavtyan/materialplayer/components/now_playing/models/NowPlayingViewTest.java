package com.edavtyan.materialplayer.components.now_playing.models;

import android.annotation.SuppressLint;

import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingFactory;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingPresenter;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public abstract class NowPlayingViewTest extends ActivityTest {
	protected static NowPlayingActivity activity;

	protected NowPlayingPresenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(NowPlayingPresenter.class);

		NowPlayingFactory factory = mock(NowPlayingFactory.class);
		when(factory.getPresenter()).thenReturn(presenter);

		app.setNowPlayingFactory(factory);

		if (activity == null) {
			activity = startActivity(NowPlayingActivity.class);
		}
	}
}
