package com.edavtyan.materialplayer.components.player_notification;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerNotificationFactoryTest extends FactoryTest {
	private PlayerNotificationFactory factory;

	@Override public void beforeEach() {
		super.beforeEach();
		factory = new PlayerNotificationFactory(context, R.layout.notification);
	}

	@Test public void provideLayoutId_returnGivenLayoutId() {
		assertThat(factory.provideLayoutId()).isEqualTo(R.layout.notification);
	}

	@Test public void testProviders() throws Exception {
		testFactoryMethod(factory::provideNotification);
		testFactoryMethod(factory::provideRemoteViews);
		testFactoryMethod(factory::provideManager);
		testFactoryMethod(factory::provideBaseManager);
		testFactoryMethod(factory::provideBuilder);
		testFactoryMethod(factory::provideModel);
		testFactoryMethod(factory::providePresenter);
		testFactoryMethod(factory::providePendingIntents);
	}
}
