package com.edavtyan.materialplayer.components.player_notification;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerNotificationFactoryTest extends BaseTest {
	private PlayerNotificationFactory factory;

	@Override public void beforeEach() {
		super.beforeEach();
		factory = new PlayerNotificationFactory(context, R.layout.notification);
	}

	@Test public void provideLayoutId_returnGivenLayoutId() {
		assertThat(factory.provideLayoutId()).isEqualTo(R.layout.notification);
	}

	@Test public void provideNotification_returnSameNotificationEachTime() throws Exception {
		testFactoryMethod(factory::provideNotification);
	}

	@Test public void provideRemoteViews_returnSameRemoteViewsEachTime() throws Exception {
		testFactoryMethod(factory::provideRemoteViews);
	}

	@Test public void provideManager_returnSameManagerEachTime() throws Exception {
		testFactoryMethod(factory::provideManager);
	}

	@Test public void provideBaseManager_returnSameBaseManagerEachTime() throws Exception {
		testFactoryMethod(factory::provideBaseManager);
	}

	@Test public void provideBuilder_returnSameBuilderEachTime() throws Exception {
		testFactoryMethod(factory::provideBuilder);
	}

	@Test public void provideModel_returnSameModelEachTime() throws Exception {
		testFactoryMethod(factory::provideModel);
	}

	@Test public void providePresenter_returnSamePresenterEachTime() throws Exception {
		testFactoryMethod(factory::providePresenter);
	}

	private <T> void testFactoryMethod(Callable<T> callable) throws Exception {
		assertThat(callable.call()).isSameAs(callable.call());
	}
}
