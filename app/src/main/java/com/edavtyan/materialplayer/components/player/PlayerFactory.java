package com.edavtyan.materialplayer.components.player;

import android.content.Context;
import android.media.MediaPlayer;

import com.edavtyan.materialplayer.components.player.engines.AudioEngine;
import com.edavtyan.materialplayer.components.player.engines.StandardAudioEngine;
import com.edavtyan.materialplayer.components.player.managers.AudioFocusManager;
import com.edavtyan.materialplayer.components.player.managers.MediaSessionManager;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory extends BaseFactory {
	private ArrayList<Track> queueList;
	private PlayerQueue queue;
	private AudioEngine audioEngine;
	private Player player;
	private PlayerPrefs playerPrefs;
	private AudioFocusManager audioFocusManager;
	private MediaSessionManager mediaSessionManager;

	public PlayerFactory(Context context) {
		super(context);
	}

	public Player getPlayer() {
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

	public List<Track> getQueueList() {
		if (queueList == null)
			queueList = new ArrayList<>();
		return queueList;
	}

	public PlayerQueue getQueue() {
		if (queue == null)
			queue = new PlayerQueue(getQueueList());
		return queue;
	}

	public AudioEngine getAudioEngine() {
		if (audioEngine == null)
			audioEngine = new StandardAudioEngine(new MediaPlayer());
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
