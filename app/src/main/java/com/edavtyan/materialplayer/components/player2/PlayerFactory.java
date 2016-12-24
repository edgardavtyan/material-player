package com.edavtyan.materialplayer.components.player2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.OpenSLAmplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.OpenSLBassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.OpenSLSurround;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.HQEqualizer;
import com.edavtyan.materialplayer.components.player2.receivers.FastForwardReceiver;
import com.edavtyan.materialplayer.components.player2.receivers.PlayPauseReceiver;
import com.edavtyan.materialplayer.components.player2.receivers.RewindReceiver;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.IBasicMediaPlayer;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerContext;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory extends BaseFactory {
	private AdvancedSharedPrefs prefs;
	private Amplifier amplifier;
	private ArrayList<Track> queueList;
	private BassBoost bassBoost;
	private HQEqualizer equalizer;
	private IBasicMediaPlayer openslPlayer;
	private OpenSLAudioEngine audioEngine;
	private OpenSLMediaPlayerContext.Parameters params;
	private OpenSLMediaPlayerFactory openslFactory;
	private Player player;
	private Queue queue;
	private SharedPreferences basePrefs;
	private Surround surround;
	private FastForwardReceiver fastForwardReceiver;
	private RewindReceiver rewindReceiver;
	private PlayPauseReceiver playPauseReceiver;

	public PlayerFactory(Context context) {
		super(context);
	}

	public FastForwardReceiver provideFastForwardReceiver() {
		if (fastForwardReceiver == null)
			fastForwardReceiver = new FastForwardReceiver(providePlayer());
		return fastForwardReceiver;
	}

	public RewindReceiver provideRewindReceiver() {
		if (rewindReceiver == null)
			rewindReceiver = new RewindReceiver(providePlayer());
		return rewindReceiver;
	}

	public PlayPauseReceiver providePlayPauseReceiver() {
		if (playPauseReceiver == null)
			playPauseReceiver = new PlayPauseReceiver(providePlayer());
		return playPauseReceiver;
	}

	public PlayerMvp.Player providePlayer() {
		if (player == null)
			player = new Player(
					provideAudioEngine(),
					provideQueue(),
					providePrefs());
		return player;
	}

	public Equalizer provideEqualizer() {
		if (equalizer == null)
			equalizer = new HQEqualizer(
					provideOpenSLFactory().createHQEqualizer(),
					providePrefs());
		return equalizer;
	}

	public Amplifier provideAmplifier() {
		if (amplifier == null)
			amplifier = new OpenSLAmplifier(
					provideOpenSLFactory().createPreAmp(),
					providePrefs());
		return amplifier;
	}

	public BassBoost provideBassBoost() {
		if (bassBoost == null)
			bassBoost = new OpenSLBassBoost(
					provideOpenSLFactory().createBassBoost(provideOpenSLPlayer()),
					providePrefs());
		return bassBoost;
	}

	public Surround provideSurround() {
		if (surround == null)
			surround = new OpenSLSurround(
					provideOpenSLFactory().createVirtualizer(provideOpenSLPlayer()),
					providePrefs());
		return surround;
	}

	public OpenSLMediaPlayerContext.Parameters provideOpenSLParams() {
		if (params == null) {
			params = new OpenSLMediaPlayerContext.Parameters();
			params.options =
					OpenSLMediaPlayerContext.OPTION_USE_HQ_EQUALIZER
					| OpenSLMediaPlayerContext.OPTION_USE_BASSBOOST
					| OpenSLMediaPlayerContext.OPTION_USE_PREAMP
					| OpenSLMediaPlayerContext.OPTION_USE_VIRTUALIZER;
			params.shortFadeDuration = 100;
			params.longFadeDuration = 100;
		}
		return params;
	}

	public SharedPreferences provideBasePrefs() {
		if (basePrefs == null)
			basePrefs = PreferenceManager.getDefaultSharedPreferences(provideContext());
		return basePrefs;
	}

	public AdvancedSharedPrefs providePrefs() {
		if (prefs == null)
			prefs = new AdvancedSharedPrefs(provideBasePrefs());
		return prefs;
	}

	public OpenSLMediaPlayerFactory provideOpenSLFactory() {
		if (openslFactory == null)
			openslFactory = new OpenSLMediaPlayerFactory(provideContext(), provideOpenSLParams());
		return openslFactory;
	}

	public List<Track> provideQueueList() {
		if (queueList == null)
			queueList = new ArrayList<>();
		return queueList;
	}

	public PlayerMvp.Queue provideQueue() {
		if (queue == null)
			queue = new Queue(provideQueueList());
		return queue;
	}

	public PlayerMvp.AudioEngine provideAudioEngine() {
		if (audioEngine == null)
			audioEngine = new OpenSLAudioEngine(provideOpenSLPlayer());
		return audioEngine;
	}

	public IBasicMediaPlayer provideOpenSLPlayer() {
		if (openslPlayer == null)
			openslPlayer = provideOpenSLFactory().createMediaPlayer();
		return openslPlayer;
	}
}
