package com.edavtyan.materialplayer.app;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.app.models.player.MusicPlayer;
import com.edavtyan.materialplayer.app.models.effects.Amplifier;
import com.edavtyan.materialplayer.app.models.effects.BassBoost;
import com.edavtyan.materialplayer.app.models.effects.StrengthBasedEffect;
import com.edavtyan.materialplayer.app.models.effects.Surround;
import com.edavtyan.materialplayer.app.models.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.app.models.effects.equalizer.HQEqualizer;
import com.h6ah4i.android.media.IBasicMediaPlayer;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerContext;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerFactory;

import lombok.Getter;

public class MusicPlayerService
		extends Service
		implements MusicPlayer.OnPreparedListener, MusicPlayer.OnPlaybackStateChangedListener {
	private static final int NOTIFICATION_ID = 1;

	public static final String ACTION_PLAY_PAUSE = "com.edavtyan.materialplayer.app.playpause";
	public static final String ACTION_FAST_FORWARD = "com.edavtyan.materialplayer.app.fastforward";
	public static final String ACTION_REWIND = "com.edavtyan.materialplayer.app.rewind";

	public static final String SEND_PLAY = "com.edavtyan.materialplayer.app.play";
	public static final String SEND_PAUSE = "com.edavtyan.materialplayer.app.pause";
	public static final String SEND_NEW_TRACK = "com.edavtyan.materialplayer.app.newTrack";

	/*
	 * Fields
	 */

	private NowPlayingNotification notification;
	private @Getter MusicPlayer player;
	private @Getter Equalizer equalizer;
	private @Getter StrengthBasedEffect surround;
	private @Getter Amplifier amplifier;
	private @Getter BassBoost bassBoost;

	/*
	 * Broadcast Receivers
	 */

	private class PlayPauseReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (player.isPlaying()) {
				player.pause();
			} else {
				player.resume();
			}
		}
	}

	private class FastForwardReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (player.hasData()) {
				player.moveNext();
				player.prepare();
			}
		}
	}

	private class RewindReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (player.hasData()) {
				player.movePrev();
				player.prepare();
			}
		}
	}

	/*
	 * MusicPlayer
	 */

	@Override
	public void onPlaybackStateChanged(MusicPlayer.PlaybackState state) {
		switch (state) {
		case PAUSED:
			sendBroadcast(new Intent(SEND_PAUSE));
			break;
		case RESUMED:
			sendBroadcast(new Intent(SEND_PLAY));
			break;
		}
	}

	public void onPrepared() {
		sendBroadcast(new Intent(SEND_NEW_TRACK));
	}

	/*
	 * Service
	 */

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return new MusicPlayerBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(NOTIFICATION_ID, notification.build());
		return START_NOT_STICKY;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		OpenSLMediaPlayerContext.Parameters params = new OpenSLMediaPlayerContext.Parameters();
		params.options =
				OpenSLMediaPlayerContext.OPTION_USE_HQ_EQUALIZER |
				OpenSLMediaPlayerContext.OPTION_USE_VIRTUALIZER |
				OpenSLMediaPlayerContext.OPTION_USE_PREAMP |
				OpenSLMediaPlayerContext.OPTION_USE_BASSBOOST;
		params.shortFadeDuration = 200;
		params.longFadeDuration = 200;
		OpenSLMediaPlayerFactory factory = new OpenSLMediaPlayerFactory(this, params);

		IBasicMediaPlayer basicPlayer = factory.createMediaPlayer();
		player = new MusicPlayer(this, basicPlayer);
		player.setOnPreparedListener(this);
		equalizer = new HQEqualizer(this, factory.createHQEqualizer());
		surround = new Surround(this, factory.createVirtualizer(basicPlayer));
		amplifier = new Amplifier(this, factory.createPreAmp());
		bassBoost = new BassBoost(this, factory.createBassBoost(basicPlayer));

		notification = new NowPlayingNotification(this);

		registerReceiver(new PlayPauseReceiver(), new IntentFilter(ACTION_PLAY_PAUSE));
		registerReceiver(new FastForwardReceiver(), new IntentFilter(ACTION_FAST_FORWARD));
		registerReceiver(new RewindReceiver(), new IntentFilter(ACTION_REWIND));
	}

	public class MusicPlayerBinder extends Binder {
		public MusicPlayerService getService() {
			return MusicPlayerService.this;
		}
	}
}
