package com.edavtyan.materialplayer.components.player2;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.Equalizer;
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
	private @Getter Amplifier amplifier;
	private @Getter Surround surround;
	private @Getter BassBoost bassBoost;

	private BroadcastReceiver playPauseReceiver = new BroadcastReceiver() {
		@Override public void onReceive(Context context, Intent intent) {
			player.playPause();
		}
	};

	private BroadcastReceiver fastForwardReceiver = new BroadcastReceiver() {
		@Override public void onReceive(Context context, Intent intent) {
			player.playNext();
		}
	};

	private BroadcastReceiver rewindReceiver = new BroadcastReceiver() {
		@Override public void onReceive(Context context, Intent intent) {
			player.rewind();
		}
	};

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
		amplifier = playerFactory.provideAmplifier();
		bassBoost = playerFactory.provideBassBoost();
		surround = playerFactory.provideSurround();

		PlayerNotificationFactory notificationFactory
				= app.getPlayerNotificationFactory(this, R.layout.notification);
		notification = notificationFactory.provideNotification();
		presenter = notificationFactory.providePresenter();
		presenter.onCreate();

		registerReceiver(playPauseReceiver, new IntentFilter(ACTION_PLAY_PAUSE));
		registerReceiver(fastForwardReceiver, new IntentFilter(ACTION_FAST_FORWARD));
		registerReceiver(rewindReceiver, new IntentFilter(ACTION_REWIND));
	}

	@Override public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}
}
