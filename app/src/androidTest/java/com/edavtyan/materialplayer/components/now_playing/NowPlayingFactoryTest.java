package com.edavtyan.materialplayer.components.now_playing;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.spy;

public class NowPlayingFactoryTest extends FactoryTest {
	private NowPlayingFactory nowPlayingFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		NowPlayingActivity activity = spy(startActivity(NowPlayingActivity.class));
		nowPlayingFactory = new NowPlayingFactory(activity);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(nowPlayingFactory::getModel);
		testFactoryMethod(nowPlayingFactory::getPresenter);
		testFactoryMethod(nowPlayingFactory::getActivity);
		testFactoryMethod(nowPlayingFactory::getArt);
		testFactoryMethod(nowPlayingFactory::getControls);
		testFactoryMethod(nowPlayingFactory::getFab);
		testFactoryMethod(nowPlayingFactory::getInfo);
		testFactoryMethod(nowPlayingFactory::getSeekbar);
	}
}
