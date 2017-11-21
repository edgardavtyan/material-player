package com.edavtyan.materialplayer.components.notification;

import android.app.Notification;
import android.content.ServiceConnection;
import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Track;

@SuppressWarnings("unused")
public interface PlayerNotificationMvp extends ServiceConnection {
	interface Model extends ServiceConnection {
		interface OnNewTrackListener {
			void onNewTrack();
		}

		interface OnPlayPauseListener {
			void onPlayPause();
		}

		void setOnNewTrackListener(OnNewTrackListener listener);
		void setOnPlayPauseListener(OnPlayPauseListener listener);

		void bind();
		void unbind();
		boolean isPlaying();
		Track getTrack();
		Bitmap getArt();
	}

	interface View {
		void setTitle(String title);
		void setInfo(String artist, String album);
		void setArt(Bitmap art);
		void setIsPlaying(boolean isPlaying);
		void update();
		Notification getNotification();
	}

	interface Presenter extends Model.OnNewTrackListener, Model.OnPlayPauseListener {
		void onCreate();
		void onDestroy();
	}
}
