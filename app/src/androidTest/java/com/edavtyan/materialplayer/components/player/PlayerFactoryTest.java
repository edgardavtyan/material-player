package com.edavtyan.materialplayer.components.player;

import android.os.Build;
import android.support.test.filters.SdkSuppress;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

public class PlayerFactoryTest extends FactoryTest {
	private PlayerFactory playerFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		playerFactory = new PlayerFactory(context);
	}

	@Test
	@SdkSuppress(minSdkVersion = Build.VERSION_CODES.KITKAT)
	public void factoryTest() throws Exception {
		testFactoryMethod(playerFactory::getPlayer);
		testFactoryMethod(playerFactory::getPlayerPrefs);
		testFactoryMethod(playerFactory::getQueue);
		testFactoryMethod(playerFactory::getQueueList);
		testFactoryMethod(playerFactory::getAudioEngine);
	}
}
