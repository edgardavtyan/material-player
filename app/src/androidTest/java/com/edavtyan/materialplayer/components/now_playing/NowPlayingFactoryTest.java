package com.edavtyan.materialplayer.components.now_playing;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class NowPlayingFactoryTest extends FactoryTest {
	private NowPlayingFactory nowPlayingFactory;

	@Override public void beforeEach() {
		super.beforeEach();
		NowPlayingMvp.View view = mock(NowPlayingMvp.View.class);
		NowPlayingActivity activity = mock(NowPlayingActivity.class);
		nowPlayingFactory = new NowPlayingFactory(activity, view);
	}

	@Test public void testProviders() throws Exception {
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
