package com.edavtyan.materialplayer.components.artist_detail;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ArtistDetailFactoryTest extends FactoryTest {
	private ArtistDetailFactory albumListFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		ArtistDetailMvp.View view = mock(ArtistDetailMvp.View.class);
		albumListFactory = new ArtistDetailFactory(context, view, "artist");
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(albumListFactory::provideAdapter);
		testFactoryMethod(albumListFactory::provideModel);
		testFactoryMethod(albumListFactory::provideView);
		testFactoryMethod(albumListFactory::providePresenter);
		testFactoryMethod(albumListFactory::provideArtistArtProvider);
		testFactoryMethod(albumListFactory::provideLastfmApi);
		testFactoryMethod(albumListFactory::provideMemoryCache);
		testFactoryMethod(albumListFactory::provideFileStorage);
	}
}
