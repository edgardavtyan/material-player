package com.edavtyan.materialplayer.components.player.engines;

import java.io.IOException;

import lombok.Setter;

public abstract class AudioEngine {
	protected @Setter OnCompletionListener onCompletionListener;
	protected @Setter OnPreparedListener onPreparedListener;

	/* Event listeners */

	public interface OnCompletionListener {
		void onCompletion();
	}

	public interface OnPreparedListener {
		void onPrepared();
	}

	/* Abstract methods */

	public abstract void reset();
	public abstract void setDataSource(String path) throws IOException;
	public abstract void prepare() throws IOException;
	public abstract void start();
	public abstract void pause();
	public abstract boolean isPlaying();
	public abstract int getCurrentPosition();
	public abstract int getDuration();
	public abstract void seekTo(int position);
}
