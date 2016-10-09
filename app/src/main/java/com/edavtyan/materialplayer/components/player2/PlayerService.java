package com.edavtyan.materialplayer.components.player2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.NowPlayingNotification;
import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.Equalizer;

import lombok.Getter;

public class PlayerService extends Service {
	public class PlayerBinder extends Binder {
		public PlayerService getService() {
			return PlayerService.this;
		}
	}
	private NowPlayingNotification notification;
	private @Getter PlayerMvp.Player player;
	private @Getter Equalizer equalizer;
	private @Getter Amplifier amplifier;
	private @Getter Surround surround;
	private @Getter BassBoost bassBoost;

	@Override
	public IBinder onBind(Intent intent) {
		return new PlayerBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(0, notification.build());
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
		notification = playerFactory.provideNotification();
	}
}
