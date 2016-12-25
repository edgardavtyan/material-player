package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

public class PlayerFactoryTest extends FactoryTest {
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
		testFactoryMethod(playerFactory::provideEqualizer);
		testFactoryMethod(playerFactory::provideBassBoost);
		testFactoryMethod(playerFactory::provideSurround);
	}
}
