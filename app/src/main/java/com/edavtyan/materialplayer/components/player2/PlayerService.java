package com.edavtyan.materialplayer.components.player2;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.player_notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.components.player_notification.PlayerNotificationMvp;

import lombok.Getter;

public class PlayerService extends Service {
	public static final String ACTION_PLAY_PAUSE = "action_playPause";
	public static final String ACTION_FAST_FORWARD = "action_fastForward";
	public static final String ACTION_REWIND = "action_rewind";

	public class PlayerBinder extends Binder {
		public PlayerService getService() {
			return PlayerService.this;
		}
	}

	private PlayerNotificationMvp.View notification;
	private PlayerNotificationMvp.Presenter presenter;
	private @Getter PlayerMvp.Player player;
	private @Getter Equalizer equalizer;
	private @Getter Surround surround;
	private @Getter BassBoost bassBoost;

	@Override
	public IBinder onBind(Intent intent) {
		return new PlayerBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(1, notification.getNotification());
		return START_NOT_STICKY;
	}

	@Override public void onCreate() {
		super.onCreate();

		App app = (App) getApplicationContext();
		PlayerFactory playerFactory = app.getPlayerFactory(this);
		player = playerFactory.providePlayer();
		equalizer = playerFactory.provideEqualizer();
		bassBoost = playerFactory.provideBassBoost();
		surround = playerFactory.provideSurround();

		BroadcastReceiver rewindReceiver = playerFactory.provideRewindReceiver();
		BroadcastReceiver playPauseReceiver = playerFactory.providePlayPauseReceiver();
		BroadcastReceiver fastForwardReceiver = playerFactory.provideFastForwardReceiver();

		registerReceiver(rewindReceiver, new IntentFilter(ACTION_REWIND));
		registerReceiver(playPauseReceiver, new IntentFilter(ACTION_PLAY_PAUSE));
		registerReceiver(fastForwardReceiver, new IntentFilter(ACTION_FAST_FORWARD));

		PlayerNotificationFactory notificationFactory
				= app.getPlayerNotificationFactory(this, R.layout.notification);
		notification = notificationFactory.provideNotification();
		presenter = notificationFactory.providePresenter();
		presenter.onCreate();
	}

	@Override public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}
}
