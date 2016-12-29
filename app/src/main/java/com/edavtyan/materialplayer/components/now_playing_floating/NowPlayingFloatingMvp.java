package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.ServiceConnection;
import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Track;

@SuppressWarnings("unused")
public interface NowPlayingFloatingMvp {
	interface Model extends ServiceConnection {
		interface OnNewTrackListener {
			void onNewTrack();
		}

		interface OnServiceConnectedListener {
			void onServiceConnected();
		}

		void bind();
		void unbind();
		Track getNowPlayingTrack();
		Bitmap getNowPlayingTrackArt();
		boolean isPlaying();
		void togglePlayPause();
		boolean hasData();
		void setOnNewTrackListener(OnNewTrackListener listener);
		void setOnServiceConnectedListener(OnServiceConnectedListener listener);
	}

	interface View {
		void setTrackTitle(String title);
		void setTrackInfo(String artistTitle, String albumTitle);
		void setArt(Bitmap art);
		void setIsPlaying(boolean isPlaying);
		void gotoNowPlaying();
		void setIsVisible(boolean visibility);
	}

	interface Presenter extends Model.OnNewTrackListener, Model.OnServiceConnectedListener {
		void onCreate();
		void onDestroy();
		void onViewClick();
		void onPlayPauseClick();
		void onNewTrack();
	}
}
