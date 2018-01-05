package com.edavtyan.materialplayer.components.lists.album_list;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AlbumListFactoryTest extends FactoryTest {
	private AlbumListFactory albumListFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		AlbumListView view = mock(AlbumListView.class);
		albumListFactory = new AlbumListFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(albumListFactory::getAdapter);
		testFactoryMethod(albumListFactory::getModel);
		testFactoryMethod(albumListFactory::getView);
		testFactoryMethod(albumListFactory::getPresenter);
	}
}
