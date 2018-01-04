package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AudioEffectsViewFactoryTest extends FactoryTest {
	private AudioEffectsViewFactory factory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		AudioEffectsActivity view = mock(AudioEffectsActivity.class);
		factory = new AudioEffectsViewFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(factory::getPresenter);
		testFactoryMethod(factory::getModel);
	}
}
