package com.edavtyan.materialplayer.components.album_detail;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AlbumDetailFactoryTest extends FactoryTest {
	private AlbumDetailFactory albumDetailFactory;

	@Override public void beforeEach() {
		super.beforeEach();
		AlbumDetailActivity view = mock(AlbumDetailActivity.class);
		albumDetailFactory = new AlbumDetailFactory(view, 0);
	}

	@Test public void testProviders() throws Exception {
		testFactoryMethod(albumDetailFactory::providePresenter);
		testFactoryMethod(albumDetailFactory::provideAdapter);
		testFactoryMethod(albumDetailFactory::provideView);
		testFactoryMethod(albumDetailFactory::provideModel);
		testFactoryMethod(albumDetailFactory::provideAlbumDB);
		testFactoryMethod(albumDetailFactory::provideTrackDB);
	}
}
