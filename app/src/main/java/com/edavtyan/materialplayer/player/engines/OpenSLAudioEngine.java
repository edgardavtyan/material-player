package com.edavtyan.materialplayer.player.engines;

import com.h6ah4i.android.media.IBasicMediaPlayer;

import java.io.IOException;

import lombok.Setter;

public class OpenSLAudioEngine implements AudioEngine {

	private final IBasicMediaPlayer player;

	private @Setter OnPreparedListener onPreparedListener;
	private @Setter OnCompletedListener onCompletedListener;

	private boolean isPrepared;
	private boolean isPrepareOnly;
	private boolean isExplicitPlayTrackCall;

	public OpenSLAudioEngine(IBasicMediaPlayer player) {
		this.player = player;
		this.player.setOnPreparedListener(mp -> onPrepared());
		this.player.setOnCompletionListener(mp -> onCompletion());
	}

	private void onPrepared() {
		isPrepared = true;

		if (isPrepareOnly) {
			isPrepareOnly = false;
		} else {
			player.start();
		}

		if (onPreparedListener != null) {
			onPreparedListener.onPrepared();
		}
	}

	private void onCompletion() {
		if (!isExplicitPlayTrackCall) {
			return;
		}

		if (onCompletedListener != null) {
			onCompletedListener.onCompleted();
		}
	}

	@Override
	public int getSessionId() {
		return player.getAudioSessionId();
	}

	@Override
	public void play() {
		player.start();
	}

	@Override
	public void pause() {
		player.pause();
	}

	@Override
	public void prepareTrack(String trackPath) {
		isPrepareOnly = true;
		playTrack(trackPath);
	}

	@Override
	public void playTrack(String trackPath) {
		try {
			isExplicitPlayTrackCall = true;
			player.reset();
			player.setDataSource(trackPath);
			isPrepared = false;
			player.prepareAsync();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setVolume(float volume) {
		player.setVolume(volume, volume);
	}

	@Override
	public long getSeek() {
		if (!isPrepared) {
			return 0;
		}

		return player.getCurrentPosition();
	}

	@Override
	public void setSeek(int seek) {
		player.seekTo(seek);
	}

	@Override
	public boolean isPlaying() {
		return player.isPlaying();
	}

	@Override
	public void reset() {
		player.reset();
	}
}