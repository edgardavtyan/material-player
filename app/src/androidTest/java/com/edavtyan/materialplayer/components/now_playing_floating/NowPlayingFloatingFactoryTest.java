package com.edavtyan.materialplayer.components.now_playing_floating;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class NowPlayingFloatingFactoryTest extends FactoryTest {
	private NowPlayingFloatingFactory nowPlayingFloatingFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		NowPlayingFloatingFragment view = mock(NowPlayingFloatingFragment.class);
		nowPlayingFloatingFactory = new NowPlayingFloatingFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(nowPlayingFloatingFactory::getModel);
		testFactoryMethod(nowPlayingFloatingFactory::getPresenter);
	}
}
