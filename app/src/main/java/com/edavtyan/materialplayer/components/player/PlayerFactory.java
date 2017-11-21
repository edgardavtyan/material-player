package com.edavtyan.materialplayer.components.player;

import android.content.Context;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.AmplifierPrefs;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoostPrefs;
import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.EqualizerBase;
import com.edavtyan.materialplayer.components.audioeffects.models.EqualizerPrefs;
import com.edavtyan.materialplayer.components.audioeffects.models.StandardAmplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.StandardBassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.StandardEqualizer;
import com.edavtyan.materialplayer.components.audioeffects.models.StandardEqualizerBase;
import com.edavtyan.materialplayer.components.audioeffects.models.StandardSurround;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.SurroundPrefs;
import com.edavtyan.materialplayer.components.audioeffects.models.eq_presets.PresetsPrefs;
import com.edavtyan.materialplayer.components.player.engines.AudioEngine;
import com.edavtyan.materialplayer.components.player.engines.ExtendedAudioEngine;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

import net.protyposis.android.mediaplayer.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory extends BaseFactory {
	private ArrayList<Track> queueList;
	private Queue queue;
	private BassBoost bassBoost;
	private BassBoostPrefs bassBoostPrefs;
	private Equalizer equalizer;
	private EqualizerBase equalizerBase;
	private EqualizerPrefs equalizerPrefs;
	private PresetsPrefs presetsPrefs;
	private Surround surround;
	private SurroundPrefs surroundPrefs;
	private Amplifier amplifier;
	private AudioEngine audioEngine;
	private Player player;
	private PlayerPrefs playerPrefs;
	private AudioFocusManager audioFocusManager;
	private MediaSessionManager mediaSessionManager;

	public PlayerFactory(Context context) {
		super(context);
	}

	public PlayerMvp.Player getPlayer() {
		if (player == null)
			player = new Player(
					getAudioEngine(),
					getQueue(),
					getPlayerPrefs());
		return player;
	}

	public PlayerPrefs getPlayerPrefs() {
		if (playerPrefs == null)
			playerPrefs = new PlayerPrefs(getPrefs());
		return playerPrefs;
	}

	public Equalizer getEqualizer() {
		if (equalizer == null)
			equalizer = new StandardEqualizer(
					getEqualizerBase(),
					getEqualizerPrefs(),
					getPresetsPrefs());
		return equalizer;
	}

	public EqualizerBase getEqualizerBase() {
		if (equalizerBase == null)
			equalizerBase = new StandardEqualizerBase(
					new android.media.audiofx.Equalizer(0, getPlayer().getSessionId()));
		return equalizerBase;
	}

	public EqualizerPrefs getEqualizerPrefs() {
		if (equalizerPrefs == null)
			equalizerPrefs = new EqualizerPrefs(getPrefs());
		return equalizerPrefs;
	}

	public PresetsPrefs getPresetsPrefs() {
		if (presetsPrefs == null)
			presetsPrefs = new PresetsPrefs(getAdvancedGsonSharedPrefs());
		return presetsPrefs;
	}

	public BassBoost getBassBoost() {
		if (bassBoost == null)
			bassBoost = new StandardBassBoost(
					new android.media.audiofx.BassBoost(0, getPlayer().getSessionId()),
					getBassBoostPrefs());
		return bassBoost;
	}

	public BassBoostPrefs getBassBoostPrefs() {
		if (bassBoostPrefs == null)
			bassBoostPrefs = new BassBoostPrefs(getPrefs());
		return bassBoostPrefs;
	}

	public Surround getSurround() {
		if (surround == null)
			surround = new StandardSurround(
					new Virtualizer(0, getPlayer().getSessionId()),
					getSurroundPrefs());
		return surround;
	}

	public SurroundPrefs getSurroundPrefs() {
		if (surroundPrefs == null)
			surroundPrefs = new SurroundPrefs(getPrefs());
		return surroundPrefs;
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public Amplifier getAmplifier() {
		if (amplifier == null)
			amplifier = new StandardAmplifier(
					new LoudnessEnhancer(getPlayer().getSessionId()),
					new AmplifierPrefs(getPrefs()));
		return amplifier;
	}

	public List<Track> getQueueList() {
		if (queueList == null)
			queueList = new ArrayList<>();
		return queueList;
	}

	public PlayerMvp.Queue getQueue() {
		if (queue == null)
			queue = new Queue(getQueueList());
		return queue;
	}

	public AudioEngine getAudioEngine() {
		if (audioEngine == null)
			audioEngine = new ExtendedAudioEngine(new MediaPlayer());
		return audioEngine;
	}

	public AudioFocusManager getAudioFocusManager() {
		if (audioFocusManager == null)
			audioFocusManager = new AudioFocusManager(getContext(), getPlayer());
		return audioFocusManager;
	}

	public MediaSessionManager getMediaSessionManager() {
		if (mediaSessionManager == null)
			mediaSessionManager = new MediaSessionManager(getContext(), getPlayer());
		return mediaSessionManager;
	}
}
