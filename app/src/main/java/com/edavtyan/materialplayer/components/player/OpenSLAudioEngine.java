package com.edavtyan.materialplayer.components.player;

import com.h6ah4i.android.media.IBasicMediaPlayer;

import java.io.IOException;

import lombok.Setter;

public class OpenSLAudioEngine
		implements PlayerMvp.AudioEngine,
				   IBasicMediaPlayer.OnPreparedListener,
				   IBasicMediaPlayer.OnCompletionListener {

	private final IBasicMediaPlayer player;

	private @Setter OnPreparedListener onPreparedListener;
	private @Setter OnCompletedListener onCompletedListener;

	private boolean isPrepared;

	public OpenSLAudioEngine(IBasicMediaPlayer player) {
		this.player = player;
		this.player.setOnPreparedListener(this);
		this.player.setOnCompletionListener(this);
		isPrepared = false;
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
	public void playPause() {
		if (player.isPlaying()) {
			pause();
		} else {
			play();
		}
	}

	@Override
	public void playTrack(String trackPath) {
		try {
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
	public long getPosition() {
		if (!isPrepared) {
			return 0;
		}

		return player.getCurrentPosition();
	}

	@Override
	public void setPosition(int position) {
		player.seekTo(position);
	}

	@Override
	public boolean isPlaying() {
		return player.isPlaying();
	}

	@Override
	public void onPrepared(IBasicMediaPlayer mp) {
		isPrepared = true;
		player.start();
		if (onPreparedListener != null) onPreparedListener.onPrepared();
	}

	@Override
	public void onCompletion(IBasicMediaPlayer mp) {
		if (onCompletedListener != null) onCompletedListener.onCompleted();
	}
}
