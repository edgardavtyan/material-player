package com.edavtyan.materialplayer.components.track_all;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TrackListFactoryTest extends FactoryTest {
	private TrackListFactory trackListFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		TrackListMvp.View view = mock(TrackListMvp.View.class);
		trackListFactory = new TrackListFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(trackListFactory::getModel);
		testFactoryMethod(trackListFactory::getView);
		testFactoryMethod(trackListFactory::getPresenter);
		testFactoryMethod(trackListFactory::getAdapter);
		testFactoryMethod(trackListFactory::getTrackDB);
	}
}
