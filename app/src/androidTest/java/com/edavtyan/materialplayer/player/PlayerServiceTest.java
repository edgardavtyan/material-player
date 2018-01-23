package com.edavtyan.materialplayer.player;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.support.test.rule.ServiceTestRule;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.AppFactory;
import com.edavtyan.materialplayer.DaggerAppComponent;
import com.edavtyan.materialplayer.notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.notification.PlayerNotificationPresenter;
import com.edavtyan.materialplayer.service.DaggerPlayerServiceComponent;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.service.PlayerService.PlayerBinder;
import com.edavtyan.materialplayer.service.PlayerServiceComponent;
import com.edavtyan.materialplayer.service.receivers.ReceiversFactory;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerServiceTest extends BaseTest {
	private final ServiceTestRule serviceTestRule = new ServiceTestRule();

	private Player player;
	private Notification notification;
	private PlayerNotificationPresenter notificationPresenter;
	private PlayerService playerService;

	@Override
	public void beforeEach() {
		super.beforeEach();

		player = mock(Player.class);
		PlayerFactory playerFactory = mock(PlayerFactory.class, RETURNS_MOCKS);
		when(playerFactory.providePlayer(any(), any(), any())).thenReturn(player);

		notificationPresenter = mock(PlayerNotificationPresenter.class);
		PlayerNotificationFactory notificationModule = mock(PlayerNotificationFactory.class, RETURNS_MOCKS);
		when(notificationModule.providePresenter(any(), any())).thenReturn(notificationPresenter);

		AppFactory appFactory = mock(AppFactory.class, RETURNS_MOCKS);
		when(appFactory.provideContext()).thenReturn(context);
		AppComponent appComponent = DaggerAppComponent
				.builder()
				.appFactory(appFactory)
				.build();

		PlayerServiceComponent component = DaggerPlayerServiceComponent
				.builder()
				.appComponent(appComponent)
				.receiversFactory(mock(ReceiversFactory.class, RETURNS_MOCKS))
				.playerFactory(playerFactory)
				.playerNotificationFactory(notificationModule)
				.build();

		app.setPlayerServiceComponent(component);

		try {
			Intent intent = new Intent(context, PlayerService.class);
			PlayerBinder binder = (PlayerBinder) serviceTestRule.bindService(intent);
			playerService = spy(binder.getService());
			doReturn(app).when(playerService).getApplicationContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void onBind_returnPlayerBinder() {
		assertThat(playerService.onBind(null)).isInstanceOf(PlayerBinder.class);
	}

	@Test
	public void onCreate_setPlayerFromFactory() {
		runOnUiThread(() -> {
			playerService.onCreate();
			assertThat(playerService.getPlayer()).isSameAs(player);
		});
	}

	@Test
	public void onStartCommand_startForeground() {
		playerService.onStartCommand(null, 0, 0);
		verify(playerService).startForeground(0, notification);
	}

	@Test
	public void onStartCommand_returnStartNotSticky() {
		assertThat(playerService.onStartCommand(null, 0, 0)).isEqualTo(Service.START_NOT_STICKY);
	}

	@Test
	public void onDestroy_destroyPresenter() {
		runOnUiThread(() -> {
			playerService.onCreate();
			playerService.onDestroy();
			verify(notificationPresenter).onDestroy();
		});
	}
}
