package com.edavtyan.materialplayer.player.engines;

public interface AudioEngine {
	interface OnPreparedListener {
		void onPrepared();
	}

	interface OnCompletedListener {
		void onCompleted();
	}

	void setOnPreparedListener(OnPreparedListener listener);
	void setOnCompletedListener(OnCompletedListener listener);

	int getSessionId();
	void play();
	void pause();
	void prepareTrack(String trackPath);
	void playTrack(String trackPath);
	void setVolume(float volume);
	long getSeek();
	void setSeek(int seek);
	boolean isPlaying();
	void reset();
}
