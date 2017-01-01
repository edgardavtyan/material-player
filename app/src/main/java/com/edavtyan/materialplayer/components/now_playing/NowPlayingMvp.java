package com.edavtyan.materialplayer.components.now_playing;

import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.widget.SeekBar;

import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.components.player.ShuffleMode;

public interface NowPlayingMvp {
	interface Model extends ServiceConnection {
		interface OnModelBoundListener {
			void onModelBound();
		}

		interface OnNewTrackListener {
			void onNewTrack();
		}

		interface OnPlayPauseListener {
			void onPlayPause();
		}

		void setOnModelBoundListener(OnModelBoundListener listener);
		void setOnNewTrackListener(OnNewTrackListener listener);
		void setOnPlayPauseListener(OnPlayPauseListener listener);

		void bind();
		void unbind();
		RepeatMode getRepeatMode();
		ShuffleMode getShuffleMode();
		void toggleRepeatMode();
		void toggleShuffleMode();
		boolean isPlaying();
		void playPause();
		void rewind();
		void fastForward();
		void seek(int positionMS);
		int getPosition();
		int getDuration();
		String getTitle();
		String getArtist();
		String getAlbum();
		Bitmap getArt();
	}

	interface View {
		interface Controls {
			void setShuffleMode(ShuffleMode shuffleMode);
			void setRepeatMode(RepeatMode repeatMode);
			void setIsPlaying(boolean isPlaying);
		}

		interface Info {
			void setTitle(String title);
			void setInfo(String artist, String album);
		}

		interface Art {
			void setArt(Bitmap art);
		}

		interface Seekbar extends SeekBar.OnSeekBarChangeListener {
			void setPosition(int timeMS);
			void setDuration(int durationMS);
			void setPositionText(int timeMS);
			void setDurationText(int durationMS);
		}

		interface Fab {
		}

		Controls getControls();
		Info getInfo();
		Art getArt();
		Seekbar getSeekbar();
		void gotoPlaylistScreen();
	}

	interface Presenter
			extends Model.OnModelBoundListener,
					Model.OnNewTrackListener,
					Model.OnPlayPauseListener {
		void bind();
		void unbind();
		void onFabClick();
		void onShuffleClick();
		void onRewindClick();
		void onPlayPauseClick();
		void onFastForwardClick();
		void onRepeatClick();
		void onSeekChanged(int position);
		void onSeekStop(int position);
	}
}
