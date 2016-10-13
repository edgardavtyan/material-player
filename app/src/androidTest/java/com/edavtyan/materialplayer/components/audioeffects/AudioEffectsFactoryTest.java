package com.edavtyan.materialplayer.components.audioeffects;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AudioEffectsFactoryTest extends BaseTest {
	private AudioEffectsFactory factory;
	private AudioEffectsMvp.View view;

	@Override public void beforeEach() {
		super.beforeEach();
		view = mock(AudioEffectsMvp.View.class);
		factory = new AudioEffectsFactory(context, view);
	}

	@Test public void provideView_givenViewEachTime() {
		assertThat(factory.provideView()).isSameAs(view);
		assertThat(factory.provideView()).isSameAs(view);
	}

	@Test public void providePresenter_samePresenterEachTime() {
		AudioEffectsMvp.Presenter first = factory.providePresenter();
		AudioEffectsMvp.Presenter second = factory.providePresenter();
		assertThat(first).isSameAs(second);
	}

	@Test public void provideModel_sameModelEachTime() {
		AudioEffectsMvp.Model first = factory.provideModel();
		AudioEffectsMvp.Model second = factory.provideModel();
		assertThat(first).isSameAs(second);
	}
}
