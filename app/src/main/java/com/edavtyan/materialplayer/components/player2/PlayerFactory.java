package com.edavtyan.materialplayer.components.player2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.NowPlayingNotification;
import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.HQEqualizer;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.h6ah4i.android.media.IBasicMediaPlayer;
import com.h6ah4i.android.media.audiofx.IBassBoost;
import com.h6ah4i.android.media.audiofx.IEqualizer;
import com.h6ah4i.android.media.audiofx.IPreAmp;
import com.h6ah4i.android.media.audiofx.IVirtualizer;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerContext;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerFactory;

import java.util.ArrayList;

public class PlayerFactory extends BaseFactory {
	private final OpenSLMediaPlayerFactory factory;
	private final Context context;
	private Player player;
	private Queue queue;
	private OpenSLAudioEngine audioEngine;
	private HQEqualizer equalizer;
	private Amplifier amplifier;
	private BassBoost bassBoost;
	private Surround surround;
	private NowPlayingNotification notification;
	private SharedPreferences prefs;
	private IBasicMediaPlayer basePlayer;

	public PlayerFactory(Context context, OpenSLMediaPlayerContext.Parameters params) {
		super(context);
		this.context = context;

		params.options =
				OpenSLMediaPlayerContext.OPTION_USE_HQ_EQUALIZER
				| OpenSLMediaPlayerContext.OPTION_USE_BASSBOOST
				| OpenSLMediaPlayerContext.OPTION_USE_PREAMP
				| OpenSLMediaPlayerContext.OPTION_USE_VIRTUALIZER;

		factory = new OpenSLMediaPlayerFactory(context, params);
	}

	public PlayerMvp.Player providePlayer() {
		if (player == null) {
			player = new Player(provideAudioEngine(), provideQueue(), provideSharedPrefs());
		}

		return player;
	}

	public Equalizer provideEqualizer() {
		if (equalizer == null) {
			IEqualizer baseEqualizer = factory.createHQEqualizer();
			equalizer = new HQEqualizer(baseEqualizer, provideSharedPrefs());
		}

		return equalizer;
	}

	public Amplifier provideAmplifier() {
		if (amplifier == null) {
			IPreAmp baseAmplifier = factory.createPreAmp();
			amplifier = new Amplifier(baseAmplifier, provideSharedPrefs());
		}

		return amplifier;
	}

	public BassBoost provideBassBoost() {
		if (bassBoost == null) {
			IBassBoost baseBassBoost = factory.createBassBoost(provideBasePlayer());
			bassBoost = new BassBoost(baseBassBoost, provideSharedPrefs());
		}

		return bassBoost;
	}

	public Surround provideSurround() {
		if (surround == null) {
			IVirtualizer baseSurround = factory.createVirtualizer(provideBasePlayer());
			surround = new Surround(baseSurround, provideSharedPrefs());
		}

		return surround;
	}

	public NowPlayingNotification provideNotification() {
		if (notification == null) notification = new NowPlayingNotification(context);
		return notification;
	}

	private PlayerMvp.AudioEngine provideAudioEngine() {
		if (audioEngine == null) audioEngine = new OpenSLAudioEngine(provideBasePlayer());
		return audioEngine;
	}

	private PlayerMvp.Queue provideQueue() {
		if (queue == null) queue = new Queue(new ArrayList<>());
		return queue;
	}

	private IBasicMediaPlayer provideBasePlayer() {
		if (basePlayer == null) basePlayer = factory.createMediaPlayer();
		return basePlayer;
	}

	private SharedPreferences provideSharedPrefs() {
		if (prefs == null) prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs;
	}
}
