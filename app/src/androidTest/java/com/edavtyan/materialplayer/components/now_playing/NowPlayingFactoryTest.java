package com.edavtyan.materialplayer.components.now_playing;

import android.support.design.widget.FloatingActionButton;
import android.widget.SeekBar;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableImageButton;
import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class NowPlayingFactoryTest extends FactoryTest {
	private NowPlayingFactory nowPlayingFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		runOnUiThread(() -> {
			NowPlayingMvp.View view = mock(NowPlayingMvp.View.class);

			NowPlayingActivity activity = mock(NowPlayingActivity.class);
			doReturn(context.getTheme()).when(activity).getTheme();
			doReturn(new TestableImageButton(context)).when(activity).findView(R.id.shuffle);
			doReturn(new TestableImageButton(context)).when(activity).findView(R.id.rewind);
			doReturn(new TestableImageButton(context)).when(activity).findView(R.id.playPause);
			doReturn(new TestableImageButton(context)).when(activity).findView(R.id.fastForward);
			doReturn(new TestableImageButton(context)).when(activity).findView(R.id.repeat);
			doReturn(new FloatingActionButton(context)).when(activity).findView(R.id.fab);
			doReturn(new SeekBar(context)).when(activity).findView(R.id.seekbar);

			nowPlayingFactory = new NowPlayingFactory(activity, view);
		});
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(nowPlayingFactory::provideModel);
		testFactoryMethod(nowPlayingFactory::provideView);
		testFactoryMethod(nowPlayingFactory::providePresenter);
		testFactoryMethod(nowPlayingFactory::provideActivity);
		testFactoryMethod(nowPlayingFactory::provideAppColors);
		testFactoryMethod(nowPlayingFactory::provideArt);
		testFactoryMethod(nowPlayingFactory::provideControls);
		testFactoryMethod(nowPlayingFactory::provideFab);
		testFactoryMethod(nowPlayingFactory::provideInfo);
		testFactoryMethod(nowPlayingFactory::provideSeekbar);
	}
}
