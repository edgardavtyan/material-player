package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ArtistListFactoryTest extends FactoryTest {
	private ArtistListFactory albumListFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		ArtistListMvp.View view = mock(ArtistListMvp.View.class);
		albumListFactory = new ArtistListFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(albumListFactory::provideAdapter);
		testFactoryMethod(albumListFactory::provideModel);
		testFactoryMethod(albumListFactory::provideView);
		testFactoryMethod(albumListFactory::providePresenter);
		testFactoryMethod(albumListFactory::provideArtistDB);
		testFactoryMethod(albumListFactory::provideImageLoader);
		testFactoryMethod(albumListFactory::provideLastfmApi);
		testFactoryMethod(albumListFactory::provideFileStorage);
		testFactoryMethod(albumListFactory::provideMemoryCache);
	}
}
