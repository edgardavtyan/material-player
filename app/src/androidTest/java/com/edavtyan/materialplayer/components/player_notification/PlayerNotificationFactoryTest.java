package com.edavtyan.materialplayer.components.player_notification;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

public class PlayerNotificationFactoryTest extends FactoryTest {
	private PlayerNotificationFactory factory;

	@Override public void beforeEach() {
		super.beforeEach();
		factory = new PlayerNotificationFactory(context, R.layout.notification, R.layout.notification_big);
	}

	@Test public void testProviders() throws Exception {
		testFactoryMethod(factory::provideNotification);
		testFactoryMethod(factory::provideNormalRemoteViews);
		testFactoryMethod(factory::provideBigRemoteViews);
		testFactoryMethod(factory::provideBaseManager);
		testFactoryMethod(factory::provideManager);
		testFactoryMethod(factory::provideBuilder);
		testFactoryMethod(factory::provideModel);
		testFactoryMethod(factory::providePresenter);
		testFactoryMethod(factory::providePendingIntents);
	}
}
