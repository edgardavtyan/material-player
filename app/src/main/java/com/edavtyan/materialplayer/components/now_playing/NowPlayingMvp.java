package com.edavtyan.materialplayer.components.now_playing;

import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.widget.SeekBar;

import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.components.player.ShuffleMode;

@SuppressWarnings("unused")
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
		String getTitle();
		String getArtist();
		String getAlbum();
		int getDuration();
		int getPosition();
		Bitmap getArt();
		void seek(int positionMillis);
		void rewind();
		void fastForward();
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
			void setArt(Bitmap artFile);
		}

		interface Seekbar extends SeekBar.OnSeekBarChangeListener {
			void setTrackPosition(int timeMillis);
			void setTrackDuration(int durationMillis);
			void setTrackPositionText(int timeMillis);
			void setTrackDurationText(int durationMillis);
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
		void onTrackSeekChanged(int progress);
		void onTrackSeekStop(int position);
	}
}
