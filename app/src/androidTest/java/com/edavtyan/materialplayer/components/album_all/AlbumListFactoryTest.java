package com.edavtyan.materialplayer.components.album_all;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AlbumListFactoryTest extends FactoryTest {
	private AlbumListFactory albumListFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		AlbumListMvp.View view = mock(AlbumListMvp.View.class);
		albumListFactory = new AlbumListFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(albumListFactory::provideAdapter);
		testFactoryMethod(albumListFactory::provideAlbumDB);
		testFactoryMethod(albumListFactory::provideTrackDB);
		testFactoryMethod(albumListFactory::provideModel);
		testFactoryMethod(albumListFactory::provideView);
		testFactoryMethod(albumListFactory::providePresenter);
	}
}
