package com.edavtyan.materialplayer.components.now_playing.models;

import android.support.design.widget.FloatingActionButton;

import com.edavtyan.materialplayer.R;

import org.junit.Test;

import static org.mockito.Mockito.verify;

public class NowPlayingFabTest extends NowPlayingViewTest {
	@Override
	public void beforeEach() {
		super.beforeEach();
		new NowPlayingFab(activity, presenter);
	}

	@Test
	public void onClick_callPresenter() {
		FloatingActionButton fabView = (FloatingActionButton) activity.findViewById(R.id.fab);
		runOnUiThread(fabView::performClick);
		verify(presenter).onFabClick();
	}
}
