package com.edavtyan.materialplayer.components.now_playing.models;

import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingFactory;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;
import com.edavtyan.materialplayer.utils.AppColors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NowPlayingViewTest extends ActivityTest {
	protected static NowPlayingActivity activity;

	protected AppColors colors;
	protected NowPlayingMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		colors = new AppColors(context);
		presenter = mock(NowPlayingMvp.Presenter.class);

		NowPlayingFactory factory = mock(NowPlayingFactory.class);
		when(factory.getAppColors()).thenReturn(colors);
		when(factory.getPresenter()).thenReturn(presenter);

		app.setNowPlayingFactory(factory);

		if (activity == null) {
			activity = startActivity(NowPlayingActivity.class);
		}
	}
}
