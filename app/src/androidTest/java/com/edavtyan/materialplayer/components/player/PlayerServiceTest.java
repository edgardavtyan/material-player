package com.edavtyan.materialplayer.components.player;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.support.test.rule.ServiceTestRule;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.components.player.PlayerService.PlayerBinder;
import com.edavtyan.materialplayer.components.player_notification.PlayerNotification;
import com.edavtyan.materialplayer.components.player_notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.components.player_notification.PlayerNotificationPresenter;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerServiceTest extends BaseTest {
	private final ServiceTestRule serviceTestRule = new ServiceTestRule();

	private PlayerService playerService;
	private PlayerMvp.Player player;
	private Notification notification;
	private PlayerNotificationPresenter notificationPresenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		notification = mock(Notification.class);
		PlayerNotification nowPlayingNotification = mock(PlayerNotification.class);
		when(nowPlayingNotification.getNotification()).thenReturn(notification);

		notificationPresenter = mock(PlayerNotificationPresenter.class);
		PlayerNotificationFactory notificationFactory = mock(PlayerNotificationFactory.class);
		when(notificationFactory.getNotification()).thenReturn(nowPlayingNotification);
		when(notificationFactory.getPresenter()).thenReturn(notificationPresenter);

		player = mock(PlayerMvp.Player.class);
		PlayerFactory playerFactory = mock(PlayerFactory.class);
		when(playerFactory.getPlayer()).thenReturn(player);

		App app = mock(App.class);
		when(app.getPlayerFactory(any())).thenReturn(playerFactory);
		when(app.getPlayerNotificationFactory(any(), anyInt(), anyInt())).thenReturn(notificationFactory);

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
		assertThat(playerService.onBind(null)).isInstanceOf(PlayerService.PlayerBinder.class);
	}

	@Test
	public void onCreate_setPlayerFromFactory() {
		playerService.onCreate();
		assertThat(playerService.getPlayer()).isSameAs(player);
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
		playerService.onCreate();
		playerService.onDestroy();
		verify(notificationPresenter).onDestroy();
	}
}
