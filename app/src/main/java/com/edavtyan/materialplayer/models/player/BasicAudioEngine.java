package com.edavtyan.materialplayer.models.player;

import android.media.MediaPlayer;

import java.io.IOException;

public class BasicAudioEngine
		extends AudioEngine
		implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
	private final MediaPlayer player;

	/* Constructors */

	public BasicAudioEngine() {
		player = new MediaPlayer();
		player.setOnPreparedListener(this);
		player.setOnCompletionListener(this);
	}

	/* AudioEngine */

	@Override
	public void reset() {
		player.reset();
	}

	@Override
	public void setDataSource(String path) throws IOException {
		player.setDataSource(path);
	}

	@Override
	public void prepare() throws IOException {
		player.prepare();
	}

	@Override
	public void start() {
		player.start();
	}

	@Override
	public void pause() {
		player.pause();
	}

	@Override
	public boolean isPlaying() {
		return player.isPlaying();
	}

	@Override
	public int getCurrentPosition() {
		return player.getCurrentPosition();
	}

	@Override
	public int getDuration() {
		return player.getDuration();
	}

	@Override
	public void seekTo(int position) {
		player.seekTo(position);
	}

	/* Listeners */

	@Override
	public void onPrepared(MediaPlayer mediaPlayer) {
		onPreparedListener.onPrepared();
	}

	@Override
	public void onCompletion(MediaPlayer mediaPlayer) {
		onCompletionListener.onCompletion();
	}
}
