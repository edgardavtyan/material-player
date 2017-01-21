package com.edavtyan.materialplayer.components.now_playing;

import android.support.design.widget.FloatingActionButton;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingViewTest;

import org.junit.Test;

import static org.mockito.Mockito.verify;

public class NowPlayingFabTest extends NowPlayingViewTest {
	@Override
	public void beforeEach() {
		super.beforeEach();
		NowPlayingFab fab = new NowPlayingFab(activity, presenter);
	}

	@Test
	public void onClick_callPresenter() {
		FloatingActionButton fabView = (FloatingActionButton) activity.findViewById(R.id.fab);
		runOnUiThread(fabView::performClick);
		verify(presenter).onFabClick();
	}
}
