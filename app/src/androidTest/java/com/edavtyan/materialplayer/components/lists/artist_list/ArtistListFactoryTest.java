package com.edavtyan.materialplayer.components.lists.artist_list;

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
		testFactoryMethod(albumListFactory::getAdapter);
		testFactoryMethod(albumListFactory::getModel);
		testFactoryMethod(albumListFactory::getView);
		testFactoryMethod(albumListFactory::getPresenter);
		testFactoryMethod(albumListFactory::getImageLoader);
		testFactoryMethod(albumListFactory::getLastfmApi);
		testFactoryMethod(albumListFactory::getFileStorage);
		testFactoryMethod(albumListFactory::getMemoryCache);
	}
}
