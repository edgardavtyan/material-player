package com.edavtyan.materialplayer.player;

import com.edavtyan.materialplayer.player.engines.AudioEngine;
import com.edavtyan.materialplayer.db.Track;

import java.util.ArrayList;
import java.util.List;

public class Player
		implements AudioEngine.OnPreparedListener,
				   AudioEngine.OnCompletedListener {

	private final AudioEngine audioEngine;
	private final PlayerPrefs prefs;
	private final PlayerQueue queue;
	private final List<OnNewTrackListener> onNewTrackListeners;
	private final List<OnPlayPauseListener> onPlayPauseListeners;

	public interface OnNewTrackListener {
		void onNewTrack();
	}

	public interface OnPlayPauseListener {
		void onPlayPause();
	}

	public Player(
			AudioEngine audioEngine,
			PlayerQueue queue,
			PlayerPrefs prefs) {
		this.prefs = prefs;
		this.audioEngine = audioEngine;
		this.audioEngine.setOnPreparedListener(this);
		this.audioEngine.setOnCompletedListener(this);

		this.queue = queue;
		this.queue.setRepeatMode(prefs.getRepeatMode());
		this.queue.setShuffleMode(prefs.getShuffleMode());

		onNewTrackListeners = new ArrayList<>();
		onPlayPauseListeners = new ArrayList<>();
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
	}

	public void addManyTracks(List<Track> tracks) {
		queue.addManyTracks(tracks);
	}

	public void removeTrackAt(int position) {
		queue.removeTrackAt(position);
	}

	public void playNewTracks(List<Track> tracks, int position) {
		queue.replaceTracks(tracks, position);
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	public void playTrackAt(int position) {
		queue.setPosition(position);
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
		if (queue.isEnded()) return;
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	public void skipToPrevious() {
		if (audioEngine.getPosition() >= 5000) {
			audioEngine.setPosition(0);
		} else {
			queue.moveToPrev();
			audioEngine.playTrack(queue.getCurrentTrack().getPath());
		}
	}

	public long getPosition() {
		return audioEngine.getPosition();
	}

	public void setPosition(int position) {
		audioEngine.setPosition(position);
	}

	public ShuffleMode getShuffleMode() {
		return queue.getShuffleMode();
	}

	public RepeatMode getRepeatMode() {
		return queue.getRepeatMode();
	}

	public boolean isPlaying() {
		return audioEngine.isPlaying();
	}

	public void toggleShuffleMode() {
		queue.toggleShuffleMode();
		prefs.saveShuffleMode(getShuffleMode());
	}

	public void toggleRepeatMode() {
		queue.toggleRepeatMode();
		prefs.saveRepeatMode(getRepeatMode());
	}

	public void onPrepared() {
		for (OnNewTrackListener onNewTrackListener : onNewTrackListeners) {
			onNewTrackListener.onNewTrack();
		}
	}

	public void lowerVolume() {
		audioEngine.setVolume(0.3f);
	}

	public void restoreVolume() {
		audioEngine.setVolume(1.0f);
	}

	public void onCompleted() {
		skipToNext();
	}

	private void raisePlayPauseListener() {
		for (OnPlayPauseListener onPlayPauseListener : onPlayPauseListeners) {
			onPlayPauseListener.onPlayPause();
		}
	}
}
