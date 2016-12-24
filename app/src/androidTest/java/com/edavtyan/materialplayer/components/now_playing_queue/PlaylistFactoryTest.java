package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class PlaylistFactoryTest extends FactoryTest {
	private PlaylistFactory playlistFactory;

	@Override public void beforeEach() {
		super.beforeEach();
		PlaylistMvp.View view = mock(PlaylistMvp.View.class);
		playlistFactory = new PlaylistFactory(context, view);
	}

	@Test public void testProviders() throws Exception {
		testFactoryMethod(playlistFactory::provideModel);
		testFactoryMethod(playlistFactory::provideView);
		testFactoryMethod(playlistFactory::providePresenter);
		testFactoryMethod(playlistFactory::provideAdapter);
	}
}
