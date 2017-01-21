package com.edavtyan.materialplayer.components.now_playing;

import android.support.design.widget.FloatingActionButton;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingFabTest extends ActivityTest {
	private static NowPlayingActivity activity;
	private NowPlayingMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(NowPlayingMvp.Presenter.class);

		NowPlayingFactory factory = mock(NowPlayingFactory.class);
		when(factory.getPresenter()).thenReturn(presenter);

		app.setNowPlayingFactory(factory);

		if (activity == null) {
			activity = startActivity(NowPlayingActivity.class);
		}

		NowPlayingFab fab = new NowPlayingFab(activity, presenter);
	}

	@Test
	public void onClick_callPresenter() {
		FloatingActionButton fabView = (FloatingActionButton) activity.findViewById(R.id.fab);
		runOnUiThread(fabView::performClick);
		verify(presenter).onFabClick();
	}
}
