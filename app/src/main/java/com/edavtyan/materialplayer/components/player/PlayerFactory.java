package com.edavtyan.materialplayer.components.player;

import android.content.Context;
import android.media.audiofx.Virtualizer;

import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoostPrefs;
import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.EqualizerPrefs;
import com.edavtyan.materialplayer.components.audioeffects.models.StandardBassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.StandardEqualizer;
import com.edavtyan.materialplayer.components.audioeffects.models.StandardSurround;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.player.receivers.FastForwardReceiver;
import com.edavtyan.materialplayer.components.player.receivers.PlayPauseReceiver;
import com.edavtyan.materialplayer.components.player.receivers.RewindReceiver;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory extends BaseFactory {
	private ArrayList<Track> queueList;
	private Queue queue;
	private BassBoost bassBoost;
	private BassBoostPrefs bassBoostPrefs;
	private Equalizer equalizer;
	private EqualizerPrefs equalizerPrefs;
	private Surround surround;
	private PlayerMvp.AudioEngine audioEngine;
	private Player player;
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
			equalizer = new StandardEqualizer(
					new android.media.audiofx.Equalizer(0, providePlayer().getSessionId()),
					provideEqualizerPrefs());
		return equalizer;
	}

	public EqualizerPrefs provideEqualizerPrefs() {
		if (equalizerPrefs == null)
			equalizerPrefs = new EqualizerPrefs(providePrefs());
		return equalizerPrefs;
	}

	public BassBoost provideBassBoost() {
		if (bassBoost == null)
			bassBoost = new StandardBassBoost(
					new android.media.audiofx.BassBoost(0, providePlayer().getSessionId()),
					provideBassBoostPrefs());
		return bassBoost;
	}

	public BassBoostPrefs provideBassBoostPrefs() {
		if (bassBoostPrefs == null)
			bassBoostPrefs = new BassBoostPrefs(providePrefs());
		return bassBoostPrefs;
	}

	public Surround provideSurround() {
		if (surround == null)
			surround = new StandardSurround(
					new Virtualizer(0, providePlayer().getSessionId()),
					providePrefs());
		return surround;
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
			audioEngine = new StandardAudioEngine();
		return audioEngine;
	}
}
