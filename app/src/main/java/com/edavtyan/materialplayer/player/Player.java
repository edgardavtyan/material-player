package com.edavtyan.materialplayer.player;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.player.engines.AudioEngine;
import com.edavtyan.materialplayer.player.engines.ExtendedAudioEngine;
import com.edavtyan.materialplayer.player.engines.OpenSLAudioEngine;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private final ExtendedAudioEngine extendedAudioEngine;
	private final OpenSLAudioEngine openSLAudioEngine;
	private final PlayerPrefs prefs;
	private final PlayerQueue queue;
	private final PlayerQueueStorage queueStorage;
	private final List<OnNewTrackListener> onNewTrackListeners;
	private final List<OnPlayPauseListener> onPlayPauseListeners;
	private final ArrayList<PlayerPlugin> plugins;

	private AudioEngine audioEngine;

	private @Nullable Integer seek;
	private boolean wasPlaying;

	public interface OnNewTrackListener {
		void onNewTrack();
	}

	public interface OnPlayPauseListener {
		void onPlayPause();
	}

	public Player(
			ExtendedAudioEngine extendedAudioEngine,
			OpenSLAudioEngine openSLAudioEngine,
			PlayerQueue queue,
			PlayerPrefs prefs,
			PlayerQueueStorage queueStorage) {
		this.prefs = prefs;
		this.prefs.addOnUseAdvancedEngineListener(this, this::onUseAdvancedEngineChanged);
		this.queueStorage = queueStorage;
		this.extendedAudioEngine = extendedAudioEngine;
		this.extendedAudioEngine.setOnPreparedListener(this::onPrepared);
		this.extendedAudioEngine.setOnCompletedListener(this::onCompleted);
		this.openSLAudioEngine = openSLAudioEngine;
		this.openSLAudioEngine.setOnPreparedListener(this::onPrepared);
		this.openSLAudioEngine.setOnCompletedListener(this::onCompleted);
		this.queue = queue;
		this.queue.setRepeatMode(prefs.getRepeatMode());
		this.queue.setShuffleMode(prefs.getShuffleMode());
		this.queue.addManyTracks(queueStorage.load());
		this.onNewTrackListeners = new ArrayList<>();
		this.onPlayPauseListeners = new ArrayList<>();
		this.plugins = new ArrayList<>();

		audioEngine = prefs.getUseAdvancedEngine() ? openSLAudioEngine : extendedAudioEngine;

		if (queue.hasData()) {
			prepareTrackAt(prefs.getCurrentIndex());
		}

	}

	private void onUseAdvancedEngineChanged(boolean isAdvanced) {
		seek = (int) audioEngine.getSeek();
		int position = queue.getCurrentIndex();
		wasPlaying = isPlaying();

		audioEngine.reset();
		audioEngine = isAdvanced ? openSLAudioEngine : extendedAudioEngine;

		mute();
		prepareTrackAt(position);
	}

	private void onPrepared() {
		for (OnNewTrackListener onNewTrackListener : onNewTrackListeners) {
			onNewTrackListener.onNewTrack();
		}

		for (PlayerPlugin plugin : plugins) {
			plugin.onNewTrack(getCurrentTrack());
		}

		if (seek != null) {
			setSeek(seek);
			restoreVolume();
			seek = null;

			if (wasPlaying) {
				play();
			}
		}
	}

	private void onCompleted() {
		skipToNext();
	}

	public void addPlugin(PlayerPlugin plugin) {
		plugins.add(plugin);
		plugin.onPlayerPluginConnected(this);
	}

	public void setOnNewTrackListener(OnNewTrackListener listener) {
		onNewTrackListeners.add(listener);
	}

	public void removeOnNewTrackListener(OnNewTrackListener listener) {
		onNewTrackListeners.remove(listener);
	}

	public void setOnPlayPauseListener(OnPlayPauseListener listener) {
		onPlayPauseListeners.add(listener);
	}

	public void removeOnPlayPauseListener(OnPlayPauseListener listener) {
		onPlayPauseListeners.remove(listener);
	}

	public int getSessionId() {
		return audioEngine.getSessionId();
	}

	public void addTrack(Track track) {
		queue.addTrack(track);
		queueStorage.save(queue.getTracks());
	}

	public void addManyTracks(List<Track> tracks) {
		queue.addManyTracks(tracks);
		queueStorage.save(queue.getTracks());
	}

	public void removeTrackAt(int position) {
		queue.removeTrackAt(position);
		queueStorage.save(queue.getTracks());
	}

	public void prepareTrackAt(int position) {
		queue.setCurrentIndex(position);
		audioEngine.prepareTrack(queue.getCurrentTrack().getPath());
	}

	public void playNewTracks(List<Track> tracks, int position) {
		queue.replaceTracks(tracks, position);
		prefs.saveCurrentIndex(position);
		queueStorage.save(queue.getTracks());
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	public void playTrackAt(int position) {
		queue.setCurrentIndex(position);
		prefs.saveCurrentIndex(position);
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	public Track getTrackAt(int position) {
		return queue.getTrackAt(position);
	}

	public Track getCurrentTrack() {
		return queue.getCurrentTrack();
	}

	public int getTracksCount() {
		return queue.getTracksCount();
	}

	public boolean hasData() {
		return queue.hasData();
	}

	public boolean isPlaying() {
		return audioEngine.isPlaying();
	}

	public void play() {
		audioEngine.play();
		raisePlayPauseListener();
	}

	public void pause() {
		audioEngine.pause();
		raisePlayPauseListener();
	}

	public void playPause() {
		if (audioEngine.isPlaying()) {
			audioEngine.pause();
		} else {
			audioEngine.play();
		}

		raisePlayPauseListener();
	}

	public void skipToNext() {
		if (!hasData()) return;
		queue.moveToNext();
		prefs.saveCurrentIndex(queue.getCurrentIndex());
		if (queue.isEnded()) return;
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	public void skipToPrevious() {
		if (audioEngine.getSeek() >= 5000) {
			audioEngine.setSeek(0);
		} else {
			queue.moveToPrev();
			prefs.saveCurrentIndex(queue.getCurrentIndex());
			audioEngine.playTrack(queue.getCurrentTrack().getPath());
		}
	}

	public long getSeek() {
		return audioEngine.getSeek();
	}

	public void setSeek(int position) {
		audioEngine.setSeek(position);
	}

	public ShuffleMode getShuffleMode() {
		return queue.getShuffleMode();
	}

	public void toggleShuffleMode() {
		queue.toggleShuffleMode();
		prefs.saveShuffleMode(getShuffleMode());
	}

	public RepeatMode getRepeatMode() {
		return queue.getRepeatMode();
	}

	public void toggleRepeatMode() {
		queue.toggleRepeatMode();
		prefs.saveRepeatMode(getRepeatMode());
	}

	public void mute() {
		audioEngine.setVolume(0f);
	}

	public void lowerVolume() {
		audioEngine.setVolume(0.3f);
	}

	public void restoreVolume() {
		audioEngine.setVolume(1.0f);
	}

	private void raisePlayPauseListener() {
		for (OnPlayPauseListener onPlayPauseListener : onPlayPauseListeners) {
			onPlayPauseListener.onPlayPause();
		}
	}
}
