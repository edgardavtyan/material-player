package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class AudioEffectsMvpFactoryTest extends FactoryTest {
	private AudioEffectsMvpFactory factory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		AudioEffectsMvp.View view = mock(AudioEffectsMvp.View.class);
		factory = new AudioEffectsMvpFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(factory::getView);
		testFactoryMethod(factory::getPresenter);
		testFactoryMethod(factory::getModel);
	}
}
