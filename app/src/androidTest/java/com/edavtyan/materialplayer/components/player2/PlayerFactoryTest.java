package com.edavtyan.materialplayer.components.player2;

import com.edavtyan.materialplayer.testlib.rules.UiThreadRule;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerFactoryTest extends BaseTest {
	@Rule public UiThreadRule uiThreadRule = new UiThreadRule();

	private PlayerFactory playerFactory;

	@Override public void beforeEach() {
		super.beforeEach();
		playerFactory = new PlayerFactory(context);
	}

	@Test public void factoryTest() throws Exception {
		testFactoryMethod(playerFactory::providePlayer);
		testFactoryMethod(playerFactory::provideRewindReceiver);
		testFactoryMethod(playerFactory::providePlayPauseReceiver);
		testFactoryMethod(playerFactory::provideFastForwardReceiver);
		testFactoryMethod(playerFactory::provideQueue);
		testFactoryMethod(playerFactory::provideQueueList);
		testFactoryMethod(playerFactory::provideAudioEngine);
		testFactoryMethod(playerFactory::provideOpenSLParams);
		testFactoryMethod(playerFactory::provideEqualizer);
		testFactoryMethod(playerFactory::provideAmplifier);
		testFactoryMethod(playerFactory::provideBassBoost);
		testFactoryMethod(playerFactory::provideSurround);
		testFactoryMethod(playerFactory::provideBasePrefs);
		testFactoryMethod(playerFactory::providePrefs);
	}

	private <T> void testFactoryMethod(Callable<T> callable) throws Exception {
		assertThat(callable.call()).isSameAs(callable.call());
	}
}
