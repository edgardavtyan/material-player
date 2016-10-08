package com.edavtyan.materialplayer.components.player2;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.support.test.rule.ServiceTestRule;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.NowPlayingNotification;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.components.player2.PlayerService.PlayerBinder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerServiceTest extends BaseTest {
	ServiceTestRule serviceTestRule = new ServiceTestRule();

	private PlayerService playerService;
	private PlayerMvp.Player player;
	private Notification notification;

	@Override public void beforeEach() {
		super.beforeEach();

		player = mock(PlayerMvp.Player.class);
		notification = mock(Notification.class);
		NowPlayingNotification nowPlayingNotification = mock(NowPlayingNotification.class);
		when(nowPlayingNotification.build()).thenReturn(notification);

		PlayerFactory playerFactory = mock(PlayerFactory.class);
		when(playerFactory.providePlayer()).thenReturn(player);
		when(playerFactory.provideNotification()).thenReturn(nowPlayingNotification);

		App app = mock(App.class);
		when(app.getPlayerFactory(any(), any())).thenReturn(playerFactory);

		try {
			Intent intent = new Intent(context, PlayerService.class);
			PlayerBinder binder = (PlayerBinder) serviceTestRule.bindService(intent);
			playerService = spy(binder.getService());
			doReturn(app).when(playerService).getApplicationContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test public void onBind_returnPlayerBinder() {
		assertThat(playerService.onBind(null)).isInstanceOf(PlayerService.PlayerBinder.class);
	}

	@Test public void onCreate_setPlayerFromFactory() {
		playerService.onCreate();
		assertThat(playerService.getPlayer()).isSameAs(player);
	}

	@Test public void onStartCommand_startForeground() {
		playerService.onStartCommand(null, 0, 0);
		verify(playerService).startForeground(0, notification);
	}

	@Test public void onStartCommand_returnStartNotSticky() {
		assertThat(playerService.onStartCommand(null, 0, 0)).isEqualTo(Service.START_NOT_STICKY);
	}
}
