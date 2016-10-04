package com.edavtyan.materialplayer.components.now_playing;

import android.support.design.widget.FloatingActionButton;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingFabTest extends BaseTest {
	private NowPlayingMvp.Presenter presenter;
	private FloatingActionButton fabView;

	@Override
	public void beforeEach() {
		super.beforeEach();

		fabView = spy(new FloatingActionButton(context));

		NowPlayingActivity activity = mock(NowPlayingActivity.class);
		when(activity.findView(R.id.fab)).thenReturn(fabView);

		presenter = mock(NowPlayingMvp.Presenter.class);

		new NowPlayingFab(activity, presenter);
	}

	@Test
	public void onClick_callPresenter() {
		fabView.performClick();
		verify(presenter).onFabClick();
	}
}
