package com.edavtyan.materialplayer.components.lists.track_list;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TrackListFactoryTest extends FactoryTest {
	private TrackListFactory trackListFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		TrackListView view = mock(TrackListView.class);
		trackListFactory = new TrackListFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(trackListFactory::getModel);
		testFactoryMethod(trackListFactory::getPresenter);
		testFactoryMethod(trackListFactory::getAdapter);
	}
}
