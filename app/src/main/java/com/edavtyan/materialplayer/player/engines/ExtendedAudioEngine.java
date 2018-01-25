package com.edavtyan.materialplayer.player.engines;

import net.protyposis.android.mediaplayer.FileSource;
import net.protyposis.android.mediaplayer.MediaPlayer;

import java.io.File;
import java.io.IOException;

import lombok.Setter;

// Uses MediaPlayer-Extended library by protyposis
// https://github.com/protyposis/MediaPlayer-Extended
public class ExtendedAudioEngine
		implements AudioEngine,
				   MediaPlayer.OnPreparedListener,
				   MediaPlayer.OnCompletionListener {

	private final MediaPlayer player;

	private @Setter OnPreparedListener onPreparedListener;
	private @Setter OnCompletedListener onCompletedListener;

	private boolean prepareOnly;

	public ExtendedAudioEngine(MediaPlayer player) {
		this.player = player;
		this.player.setOnPreparedListener(this);
		this.player.setOnCompletionListener(this);
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
		prepareOnly = true;
		playTrack(trackPath);
	}

	@Override
	public void playTrack(String trackPath) {
		try {
			player.reset();
			player.setDataSource(new FileSource(new File(trackPath)));
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
	public void onPrepared(MediaPlayer mp) {
		if (prepareOnly) {
			prepareOnly = false;
		} else {
			player.start();
		}

		if (onPreparedListener != null) onPreparedListener.onPrepared();
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		if (onCompletedListener != null) onCompletedListener.onCompleted();
	}
}
