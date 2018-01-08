package com.edavtyan.materialplayer.components.player;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Binder;
import android.os.IBinder;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.components.audio_effects.amplifier.Amplifier;
import com.edavtyan.materialplayer.components.audio_effects.amplifier.AmplifierModule;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoost;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoostModule;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.EqualizerModule;
import com.edavtyan.materialplayer.components.audio_effects.surround.Surround;
import com.edavtyan.materialplayer.components.audio_effects.surround.SurroundModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotification;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationPresenter;
import com.edavtyan.materialplayer.components.player.managers.AudioFocusManager;
import com.edavtyan.materialplayer.components.player.managers.MediaSessionManager;
import com.edavtyan.materialplayer.components.player.receivers.AudioBecomingNoisyReceiver;
import com.edavtyan.materialplayer.components.player.receivers.CloseReceiver;
import com.edavtyan.materialplayer.components.player.receivers.HeadphonesConnectedReceiver;
import com.edavtyan.materialplayer.components.player.receivers.MediaButtonReceiver;
import com.edavtyan.materialplayer.components.player.receivers.PlayPauseReceiver;
import com.edavtyan.materialplayer.components.player.receivers.SkipToNextReceiver;
import com.edavtyan.materialplayer.components.player.receivers.SkipToPreviousReceiver;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;

import javax.inject.Inject;

import lombok.Getter;

public class PlayerService extends Service {
	public static final String ACTION_PLAY_PAUSE = "action_playPause";
	public static final String ACTION_FAST_FORWARD = "action_fastForward";
	public static final String ACTION_REWIND = "action_rewind";
	public static final String ACTION_CLOSE = "action_close";

	public class PlayerBinder extends Binder {
		public PlayerService getService() {
			return PlayerService.this;
		}
	}

	@Inject @Getter Player player;
	@Inject @Getter Equalizer equalizer;
	@Inject @Getter Surround surround;
	@Inject @Getter BassBoost bassBoost;
	@Inject @Getter Amplifier amplifier;
	@Inject AudioFocusManager audioFocusManager;
	@Inject MediaSessionManager mediaSessionManager;
	private PlayerNotification notification;
	private PlayerNotificationPresenter presenter;

	@Inject AudioBecomingNoisyReceiver audioBecomingNoisyReceiver;
	@Inject CloseReceiver closeReceiver;
	@Inject HeadphonesConnectedReceiver headphonesConnectedReceiver;
	@Inject MediaButtonReceiver mediaButtonReceiver;
	@Inject PlayPauseReceiver playPauseReceiver;
	@Inject SkipToNextReceiver skipToNextReceiver;
	@Inject SkipToPreviousReceiver skipToPreviousReceiver;

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

		getComponent().inject(this);

		registerReceiver(playPauseReceiver, new IntentFilter(ACTION_PLAY_PAUSE));
		registerReceiver(skipToPreviousReceiver, new IntentFilter(ACTION_REWIND));
		registerReceiver(skipToNextReceiver, new IntentFilter(ACTION_FAST_FORWARD));
		registerReceiver(closeReceiver, new IntentFilter(ACTION_CLOSE));
		registerReceiver(audioBecomingNoisyReceiver, new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
		registerReceiver(mediaButtonReceiver, new IntentFilter(Intent.ACTION_MEDIA_BUTTON));
		registerReceiver(headphonesConnectedReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));

		audioFocusManager.requestFocus();
		mediaSessionManager.init();

		PlayerNotificationFactory notificationFactory = app.getPlayerNotificationFactory(this);
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

	protected PlayerServiceComponent getComponent() {
		return DaggerPlayerServiceComponent
				.builder()
				.playerModule(new PlayerModule(this))
				.equalizerModule(new EqualizerModule())
				.amplifierModule(new AmplifierModule())
				.bassBoostModule(new BassBoostModule())
				.surroundModule(new SurroundModule())
				.advancedSharedPrefsModule(new AdvancedSharedPrefsModule())
				.build();
	}
}
