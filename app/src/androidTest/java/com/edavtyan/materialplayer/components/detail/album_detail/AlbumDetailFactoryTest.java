package com.edavtyan.materialplayer.components.detail.album_detail;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AlbumDetailFactoryTest extends FactoryTest {
	private AlbumDetailFactory albumDetailFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		albumDetailFactory = new AlbumDetailFactory(context, mock(AlbumDetailActivity.class), 0);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(albumDetailFactory::getPresenter);
		testFactoryMethod(albumDetailFactory::getAdapter);
		testFactoryMethod(albumDetailFactory::getModel);
	}
}
