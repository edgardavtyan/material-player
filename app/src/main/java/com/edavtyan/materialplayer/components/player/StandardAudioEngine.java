package com.edavtyan.materialplayer.components.player;

import android.media.MediaPlayer;

import java.io.IOException;

import lombok.Setter;

public class StandardAudioEngine implements PlayerMvp.AudioEngine, MediaPlayer.OnPreparedListener {
	private final MediaPlayer player;

	private @Setter OnPreparedListener onPreparedListener;

	public StandardAudioEngine() {
		player = new MediaPlayer();
		player.setOnPreparedListener(this);
	}

	@Override public int getSessionId() {
		return player.getAudioSessionId();
	}

	@Override public void play() {
		player.start();
	}

	@Override public void pause() {
		player.pause();
	}

	@Override public void playPause() {
		if (player.isPlaying()) pause(); else play();
	}

	@Override public void playTrack(String trackPath) {
		try {
			player.reset();
			player.setDataSource(trackPath);
			player.prepareAsync();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override public long getPosition() {
		return player.getCurrentPosition();
	}

	@Override public void setPosition(int position) {
		player.seekTo(position);
	}

	@Override public boolean isPlaying() {
		return player.isPlaying();
	}

	@Override public void onPrepared(MediaPlayer mp) {
		player.start();
		if (onPreparedListener != null) onPreparedListener.onPrepared();
	}
}