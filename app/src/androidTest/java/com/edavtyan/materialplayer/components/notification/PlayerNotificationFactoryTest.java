package com.edavtyan.materialplayer.components.notification;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

public class PlayerNotificationFactoryTest extends FactoryTest {
	private PlayerNotificationFactory factory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		factory = new PlayerNotificationFactory(context);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(factory::getNotification);
		testFactoryMethod(factory::getNormalRemoteViews);
		testFactoryMethod(factory::getBigRemoteViews);
		testFactoryMethod(factory::getBaseManager);
		testFactoryMethod(factory::getManager);
		testFactoryMethod(factory::getCompatBuilder);
		testFactoryMethod(factory::getModel);
		testFactoryMethod(factory::getPresenter);
		testFactoryMethod(factory::getPendingIntents);
	}
}
