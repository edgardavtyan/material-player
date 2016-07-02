package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.components.player.engines.AudioEngine;

import java.io.IOException;

import lombok.Setter;

public class MusicPlayer
		implements AudioEngine.OnCompletionListener, AudioEngine.OnPreparedListener {
	private final NowPlayingQueue queue;
	private AudioEngine audioEngine;
	private @Setter OnPreparedListener onPreparedListener;
	private @Setter OnPlaybackStateChangedListener onPlaybackStateChangedListener;

	/* Event listeners */

	public interface OnPreparedListener {
		void onPrepared();
	}

	public interface OnPlaybackStateChangedListener {
		void onPlaybackStateChanged(PlaybackState state);
	}

	/* Constructors */

	public MusicPlayer(AudioEngine audioEngine, NowPlayingQueue queue) {
		this.queue = queue;
		setAudioEngine(audioEngine);
	}

	/* MusicPlayer methods */

	public void setAudioEngine(AudioEngine newAudioEngine) {
		int position = 0;
		boolean isPlaying = false;
		if (audioEngine != null) {
			position = audioEngine.getCurrentPosition();
			isPlaying = audioEngine.isPlaying();
			audioEngine.reset();
		}

		audioEngine = newAudioEngine;
		audioEngine.setOnCompletionListener(this);
		audioEngine.setOnPreparedListener(this);

		if (queue.hasData()) {
			try {
				audioEngine.setDataSource(queue.getCurrentTrack().getPath());
				audioEngine.prepare();
				audioEngine.seekTo(position);
				if (isPlaying) audioEngine.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void prepare() {
		try {
			audioEngine.reset();
			audioEngine.setDataSource(queue.getCurrentTrack().getPath());
			audioEngine.prepare();
			audioEngine.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void resume() {
		audioEngine.start();
		raiseOnPlaybackStateChanged(PlaybackState.RESUMED);
	}

	public void pause() {
		audioEngine.pause();
		raiseOnPlaybackStateChanged(PlaybackState.PAUSED);
	}

	public void moveNext() {
		queue.moveToNext();
	}

	public void movePrev() {
		if (audioEngine.getCurrentPosition() > 5000) {
			audioEngine.seekTo(0);
		} else {
			queue.moveToPrev();
		}
	}

	public boolean isPlaying() {
		return audioEngine.isPlaying();
	}

	public int getDuration() {
		return audioEngine.getDuration();
	}

	public int getPosition() {
		return audioEngine.getCurrentPosition();
	}

	public void setPosition(int position) {
		audioEngine.seekTo(position);
	}

	/* OpenSLMediaPlayer event listeners */

	@Override
	public void onCompletion() {
		if (audioEngine.getCurrentPosition() < 500) return;

		switch (queue.getRepeatMode()) {
		case NO_REPEAT:
			if (queue.isAtLastTrack()) {
				audioEngine.pause();
			} else {
				moveNext();
				prepare();
			}
			break;

		case REPEAT:
			moveNext();
			prepare();
			break;

		case REPEAT_ONE:
			audioEngine.seekTo(0);
			prepare();
			break;
		}
	}

	@Override
	public void onPrepared() {
		if (onPreparedListener != null) {
			onPreparedListener.onPrepared();
		}
	}

	/* Private methods */

	private void raiseOnPlaybackStateChanged(PlaybackState state) {
		if (onPlaybackStateChangedListener != null) {
			onPlaybackStateChangedListener.onPlaybackStateChanged(state);
		}
	}
}
