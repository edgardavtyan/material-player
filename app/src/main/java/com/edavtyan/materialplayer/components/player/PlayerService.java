package com.edavtyan.materialplayer.components.player;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
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
	private AudioFocusManager audioFocusManager;
	private MediaSessionManager mediaSessionManager;
	private @Getter PlayerMvp.Player player;
	private @Getter Equalizer equalizer;
	private @Getter Surround surround;
	private @Getter BassBoost bassBoost;
	private @Getter Amplifier amplifier;

	@Override
	public IBinder onBind(Intent intent) {
		return new PlayerBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(1, notification.getNotification());
		return START_NOT_STICKY;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		App app = (App) getApplicationContext();
		PlayerFactory playerFactory = app.getPlayerFactory(this);
		player = playerFactory.getPlayer();
		equalizer = playerFactory.getEqualizer();
		bassBoost = playerFactory.getBassBoost();
		surround = playerFactory.getSurround();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			amplifier = playerFactory.getAmplifier();
		}

		BroadcastReceiver playPauseReceiver = playerFactory.getPlayPauseReceiver();
		BroadcastReceiver skipToPreviousReceiver = playerFactory.getSkipToPreviousReceiver();
		BroadcastReceiver skipToNextReceiver = playerFactory.getSkipToNextReceiver();
		BroadcastReceiver audioBecomingNoisyReceiver = playerFactory.getAudioBecomingNoisyReceiver();
		BroadcastReceiver mediaButtonReceiver = playerFactory.getMediaButtonReceiver();
		BroadcastReceiver headphonesConnectedReceiver = playerFactory.getHeadphonesConnectedReceiver();

		registerReceiver(playPauseReceiver, new IntentFilter(ACTION_PLAY_PAUSE));
		registerReceiver(skipToPreviousReceiver, new IntentFilter(ACTION_REWIND));
		registerReceiver(skipToNextReceiver, new IntentFilter(ACTION_FAST_FORWARD));
		registerReceiver(audioBecomingNoisyReceiver, new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
		registerReceiver(mediaButtonReceiver, new IntentFilter(Intent.ACTION_MEDIA_BUTTON));
		registerReceiver(headphonesConnectedReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));

		audioFocusManager = new AudioFocusManager(this, player);
		audioFocusManager.requestFocus();

		mediaSessionManager = new MediaSessionManager(this, player);
		mediaSessionManager.init();

		PlayerNotificationFactory notificationFactory = app.getPlayerNotificationFactory(
				this, R.layout.notification, R.layout.notification_big);
		notification = notificationFactory.getNotification();
		presenter = notificationFactory.getPresenter();
		presenter.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
		audioFocusManager.dropFocus();
		mediaSessionManager.close();
	}
}
