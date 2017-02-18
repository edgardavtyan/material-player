package com.edavtyan.materialplayer.components.player;

import android.app.Service;
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
		PlayerFactory factory = app.getPlayerFactory(this);
		player = factory.getPlayer();
		equalizer = factory.getEqualizer();
		bassBoost = factory.getBassBoost();
		surround = factory.getSurround();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			amplifier = factory.getAmplifier();
		}

		registerReceiver(factory.getPlayPauseReceiver(), new IntentFilter(ACTION_PLAY_PAUSE));
		registerReceiver(factory.getSkipToPreviousReceiver(), new IntentFilter(ACTION_REWIND));
		registerReceiver(factory.getSkipToNextReceiver(), new IntentFilter(ACTION_FAST_FORWARD));
		registerReceiver(factory.getAudioBecomingNoisyReceiver(), new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
		registerReceiver(factory.getMediaButtonReceiver(), new IntentFilter(Intent.ACTION_MEDIA_BUTTON));
		registerReceiver(factory.getHeadphonesConnectedReceiver(), new IntentFilter(Intent.ACTION_HEADSET_PLUG));

		audioFocusManager = factory.getAudioFocusManager();
		audioFocusManager.requestFocus();

		mediaSessionManager = factory.getMediaSessionManager();
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
