package com.edavtyan.materialplayer.components.audioeffects;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AudioEffectsFactoryTest extends FactoryTest {
	private AudioEffectsFactory factory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		AudioEffectsMvp.View view = mock(AudioEffectsMvp.View.class);
		factory = new AudioEffectsFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(factory::provideView);
		testFactoryMethod(factory::providePresenter);
		testFactoryMethod(factory::provideModel);
	}
}
