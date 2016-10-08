package com.edavtyan.materialplayer.components.player2;

import com.h6ah4i.android.media.IBasicMediaPlayer;

import java.io.IOException;

import lombok.Setter;

public class OpenSLAudioEngine implements PlayerMvp.AudioEngine {
	private final IBasicMediaPlayer baseEngine;

	private @Setter OnPreparedListener onPreparedListener;

	public OpenSLAudioEngine(IBasicMediaPlayer baseEngine) {
		this.baseEngine = baseEngine;
	}

	@Override public void play() {
		baseEngine.start();
	}

	@Override public void pause() {
		baseEngine.pause();
	}

	@Override public void playPause() {
		if (baseEngine.isPlaying()) {
			baseEngine.pause();
		} else {
			baseEngine.start();
		}
	}

	//todo: handle IOException properly
	@Override public void playTrack(String trackPath) {
		try {
			if (isPlaying()) {
				baseEngine.reset();
			}
			baseEngine.setDataSource(trackPath);
			baseEngine.prepare();
			baseEngine.start();

			if (onPreparedListener != null) onPreparedListener.onPrepared();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override public long getPosition() {
		return baseEngine.getCurrentPosition();
	}

	@Override public void setPosition(int position) {
		baseEngine.seekTo(position);
	}

	@Override public boolean isPlaying() {
		return baseEngine.isPlaying();
	}
}
