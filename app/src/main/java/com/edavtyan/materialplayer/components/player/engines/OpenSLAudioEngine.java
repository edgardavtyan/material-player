package com.edavtyan.materialplayer.components.player.engines;

import com.h6ah4i.android.media.IBasicMediaPlayer;

import java.io.IOException;

public class OpenSLAudioEngine
		extends AudioEngine
		implements IBasicMediaPlayer.OnCompletionListener, IBasicMediaPlayer.OnPreparedListener {
	private final IBasicMediaPlayer player;

	/* Constructors */

	public OpenSLAudioEngine(IBasicMediaPlayer player) {
		this.player = player;
		player.setOnCompletionListener(this);
		player.setOnPreparedListener(this);
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

	/* IBasicMediaPlayer event listeners */

	@Override
	public void onCompletion(IBasicMediaPlayer mp) {
		onCompletionListener.onCompletion();
	}

	@Override
	public void onPrepared(IBasicMediaPlayer mp) {
		onPreparedListener.onPrepared();
	}
}
