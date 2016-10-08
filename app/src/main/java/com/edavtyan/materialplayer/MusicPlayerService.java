package com.edavtyan.materialplayer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.HQEqualizer;
import com.edavtyan.materialplayer.components.player.MusicPlayer;
import com.edavtyan.materialplayer.components.player.NowPlayingQueue;
import com.edavtyan.materialplayer.components.player.PlaybackState;
import com.edavtyan.materialplayer.components.player.engines.AudioEngine;
import com.edavtyan.materialplayer.components.player.engines.BasicAudioEngine;
import com.edavtyan.materialplayer.components.player.engines.OpenSLAudioEngine;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.IBasicMediaPlayer;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerContext;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerFactory;

import java.util.List;

import lombok.Getter;

public class MusicPlayerService
		extends Service
		implements MusicPlayer.OnPreparedListener, MusicPlayer.OnPlaybackStateChangedListener,
		           SharedPreferences.OnSharedPreferenceChangeListener {
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
	private AudioEngine openslAudioEngine;
	private AudioEngine basicAudioEngine;
	private @Getter MusicPlayer player;
	private @Getter NowPlayingQueue queue;
	private @Getter Equalizer equalizer;
	private @Getter Surround surround;
	private @Getter Amplifier amplifier;
	private @Getter BassBoost bassBoost;

	public void switchQueueTrackToPosition(int position) {
		queue.setCurrentTrackIndex(position);
		player.prepare();
	}

	public void removeQueueTrackAtPosition(int position) {
		queue.remove(position);
	}

	public Track getQueueTrackAt(int position) {
		return queue.get(position);
	}

	public int getQueueCount() {
		return queue.size();
	}

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
			if (getQueue().hasData()) {
				player.moveNext();
				player.prepare();
			}
		}
	}

	private class RewindReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (getQueue().hasData()) {
				player.movePrev();
				player.prepare();
			}
		}
	}

	/*
	 * MusicPlayer
	 */

	@Override
	public void onPlaybackStateChanged(PlaybackState state) {
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

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		AdvancedSharedPrefs advancedPrefs = new AdvancedSharedPrefs(prefs);

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
		equalizer = new HQEqualizer(factory.createHQEqualizer(), advancedPrefs);
		surround = new Surround(factory.createVirtualizer(basicPlayer), advancedPrefs);
		amplifier = new Amplifier(factory.createPreAmp(), advancedPrefs);
		bassBoost = new BassBoost(factory.createBassBoost(basicPlayer), advancedPrefs);

		queue = new NowPlayingQueue(this);
		openslAudioEngine = new OpenSLAudioEngine(basicPlayer);
		basicAudioEngine = new BasicAudioEngine();

		player = new MusicPlayer(openslAudioEngine, queue);
		player.setOnPreparedListener(this);
		player.setOnPlaybackStateChangedListener(this);

		notification = new NowPlayingNotification(this);

		prefs.registerOnSharedPreferenceChangeListener(this);

		registerReceiver(new PlayPauseReceiver(), new IntentFilter(ACTION_PLAY_PAUSE));
		registerReceiver(new FastForwardReceiver(), new IntentFilter(ACTION_FAST_FORWARD));
		registerReceiver(new RewindReceiver(), new IntentFilter(ACTION_REWIND));
	}

	public class MusicPlayerBinder extends Binder {
		public MusicPlayerService getService() {
			return MusicPlayerService.this;
		}
	}

	/* SharedPreferences.onSharedPreferenceChanged */

	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		if (!key.equals(getString(R.string.pref_audio_engine_key))) return;

		String audioEnginePref = prefs.getString(key, getString(R.string.pref_audio_engine_basic));
		String basicEnginePref = getString(R.string.pref_audio_engine_basic);
		String openslEnginePref = getString(R.string.pref_audio_engine_opensl);

		if (audioEnginePref.equals(basicEnginePref)) {
			player.setAudioEngine(basicAudioEngine);
		} else if (audioEnginePref.equals(openslEnginePref)) {
			player.setAudioEngine(openslAudioEngine);
		}
	}

	/* Public methods */

	public void playQueue(List<Track> tracks, int position) {
		queue.setTracks(tracks, position);
		player.prepare();
	}

	public void addToQueue(Track track) {
		queue.add(track);
	}
}
