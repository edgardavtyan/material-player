package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.db.Track;

import java.util.List;

@SuppressWarnings("unused")
public interface PlayerMvp {
	interface Player {

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
		long getPosition();
		void setPosition(int position);
		boolean isPlaying();
		ShuffleMode getShuffleMode();
		RepeatMode getRepeatMode();
		void toggleShuffleMode();
		void toggleRepeatMode();
		void onPrepared();
		void lowerVolume();
		void restoreVolume();
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
		void setVolume(float volume);
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
		int getPosition();
		void setPosition(int position);
		ShuffleMode getShuffleMode();
		void setShuffleMode(ShuffleMode shuffleMode);
		RepeatMode getRepeatMode();
		void setRepeatMode(RepeatMode repeatMode);
		void toggleShuffleMode();
		void toggleRepeatMode();
		boolean hasData();
		boolean isEnded();
	}
}
