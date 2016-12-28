package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.db.Track;

import java.util.List;

public interface PlayerMvp {
	interface Player {
		String PREF_REPEAT_MODE = "pref_repeatMode";
		String PREF_SHUFFLE_MODE = "pref_shuffleMode";
		RepeatMode DEFAULT_REPEAT_MODE = RepeatMode.DISABLED;
		ShuffleMode DEFAULT_SHUFFLE_MODE = ShuffleMode.DISABLED;

		interface OnNewTrackListener {
			void onNewTrack();
		}

		interface OnPlayPauseListener {
			void onPlayPause();
		}

		void setOnNewTrackListener(OnNewTrackListener listener);
		void removeOnNewTrackListener(OnNewTrackListener listener);
		void setOnPlayPauseListener(OnPlayPauseListener listener);

		int getSessionId();
		void addTrack(Track track);
		void addManyTracks(List<Track> tracks);
		void removeTrackAt(int position);
		void playNewTracks(List<Track> tracks, int position);
		void playTrackAt(int position);
		Track getTrackAt(int position);
		Track getCurrentTrack();
		int getTracksCount();
		boolean hasData();
		void play();
		void pause();
		void playPause();
		void playNext();
		void rewind();
		void setPosition(int position);
		long getPosition();
		boolean isPlaying();
		ShuffleMode getShuffleMode();
		RepeatMode getRepeatMode();
		void toggleShuffleMode();
		void toggleRepeatMode();
		void onPrepared();
	}

	interface AudioEngine {
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
		void playPause();
		void playTrack(String trackPath);
		long getPosition();
		void setPosition(int position);
		boolean isPlaying();
	}

	interface Queue {
		void addTrack(Track track);
		void addManyTracks(List<Track> tracks);
		void removeTrackAt(int position);
		void clear();
		Track getTrackAt(int position);
		Track getCurrentTrack();
		int getTracksCount();
		void moveToNext();
		void moveToPrev();
		void setPosition(int position);
		int getPosition();
		void setShuffleMode(ShuffleMode shuffleMode);
		void setRepeatMode(RepeatMode repeatMode);
		ShuffleMode getShuffleMode();
		RepeatMode getRepeatMode();
		void toggleShuffleMode();
		void toggleRepeatMode();
		boolean hasData();
		boolean isEnded();
	}
}
