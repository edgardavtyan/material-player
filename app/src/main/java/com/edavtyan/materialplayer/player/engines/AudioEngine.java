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
	long getPosition();
	void setPosition(int position);
	boolean isPlaying();
}
