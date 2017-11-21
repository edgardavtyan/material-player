package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.components.player.engines.AudioEngine;
import com.edavtyan.materialplayer.db.Track;

import java.util.ArrayList;
import java.util.List;

public class Player
		implements PlayerMvp.Player,
				   AudioEngine.OnPreparedListener,
				   AudioEngine.OnCompletedListener {

	private final AudioEngine audioEngine;
	private final PlayerPrefs prefs;
	private final PlayerMvp.Queue queue;
	private final List<OnNewTrackListener> onNewTrackListeners;
	private final List<OnPlayPauseListener> onPlayPauseListeners;

	public Player(
			AudioEngine audioEngine,
			PlayerMvp.Queue queue,
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

	@Override
	public void setOnNewTrackListener(OnNewTrackListener listener) {
		onNewTrackListeners.add(listener);
	}

	@Override
	public void removeOnNewTrackListener(OnNewTrackListener listener) {
		onNewTrackListeners.remove(listener);
	}

	@Override
	public void setOnPlayPauseListener(OnPlayPauseListener listener) {
		onPlayPauseListeners.add(listener);
	}

	@Override
	public void removeOnPlayPauseListener(OnPlayPauseListener listener) {
		onPlayPauseListeners.remove(listener);
	}

	@Override
	public int getSessionId() {
		return audioEngine.getSessionId();
	}

	@Override
	public void addTrack(Track track) {
		queue.addTrack(track);
	}

	@Override
	public void addManyTracks(List<Track> tracks) {
		queue.addManyTracks(tracks);
	}

	@Override
	public void removeTrackAt(int position) {
		queue.removeTrackAt(position);
	}

	@Override
	public void playNewTracks(List<Track> tracks, int position) {
		queue.replaceTracks(tracks, position);
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	@Override
	public void playTrackAt(int position) {
		queue.setPosition(position);
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	@Override
	public Track getTrackAt(int position) {
		return queue.getTrackAt(position);
	}

	@Override
	public Track getCurrentTrack() {
		return queue.getCurrentTrack();
	}

	@Override
	public int getTracksCount() {
		return queue.getTracksCount();
	}

	@Override
	public boolean hasData() {
		return queue.hasData();
	}

	@Override
	public void play() {
		audioEngine.play();
		raisePlayPauseListener();
	}

	@Override
	public void pause() {
		audioEngine.pause();
		raisePlayPauseListener();
	}

	@Override
	public void playPause() {
		audioEngine.playPause();
		raisePlayPauseListener();
	}

	@Override
	public void skipToNext() {
		if (!hasData()) return;
		queue.moveToNext();
		if (queue.isEnded()) return;
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	@Override
	public void skipToPrevious() {
		if (audioEngine.getPosition() >= 5000) {
			audioEngine.setPosition(0);
		} else {
			queue.moveToPrev();
			audioEngine.playTrack(queue.getCurrentTrack().getPath());
		}
	}

	@Override
	public long getPosition() {
		return audioEngine.getPosition();
	}

	@Override
	public void setPosition(int position) {
		audioEngine.setPosition(position);
	}

	@Override
	public ShuffleMode getShuffleMode() {
		return queue.getShuffleMode();
	}

	@Override
	public RepeatMode getRepeatMode() {
		return queue.getRepeatMode();
	}

	@Override
	public boolean isPlaying() {
		return audioEngine.isPlaying();
	}

	@Override
	public void toggleShuffleMode() {
		queue.toggleShuffleMode();
		prefs.saveShuffleMode(getShuffleMode());
	}

	@Override
	public void toggleRepeatMode() {
		queue.toggleRepeatMode();
		prefs.saveRepeatMode(getRepeatMode());
	}

	@Override
	public void onPrepared() {
		for (OnNewTrackListener onNewTrackListener : onNewTrackListeners) {
			onNewTrackListener.onNewTrack();
		}
	}

	@Override
	public void lowerVolume() {
		audioEngine.setVolume(0.3f);
	}

	@Override
	public void restoreVolume() {
		audioEngine.setVolume(1.0f);
	}

	@Override
	public void onCompleted() {
		skipToNext();
	}

	private void raisePlayPauseListener() {
		for (OnPlayPauseListener onPlayPauseListener : onPlayPauseListeners) {
			onPlayPauseListener.onPlayPause();
		}
	}
}
