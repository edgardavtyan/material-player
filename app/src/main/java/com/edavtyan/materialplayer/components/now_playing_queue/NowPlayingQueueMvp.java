package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.ServiceConnection;

import com.edavtyan.materialplayer.db.Track;

@SuppressWarnings("unused")
public interface NowPlayingQueueMvp {
	interface Model extends ServiceConnection {
		interface OnServiceConnectedListener {
			void onServiceConnected();
		}

		void setOnServiceConnectedListener(OnServiceConnectedListener listener);

		void bind();
		void unbind();
		void playItemAtPosition(int position);
		void removeItemAtPosition(int position);
		Track getTrackAtPosition(int position);
		int getTrackCount();
	}

	interface View {
		void update();
	}

	interface Presenter extends Model.OnServiceConnectedListener {
		void onCreate();
		void onDestroy();
		void onItemClick(int position);
		void onRemoveItemClick(int position);
		void onBindViewHolder(NowPlayingQueueViewHolder holder, int position);
		int getItemCount();
	}
}
