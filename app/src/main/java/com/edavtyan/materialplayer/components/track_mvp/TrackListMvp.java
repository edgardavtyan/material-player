package com.edavtyan.materialplayer.components.track_mvp;

import com.edavtyan.materialplayer.components.tracks.Track;

public interface TrackListMvp {
	interface Model {
		interface OnServiceConnectedListener {
			void onServiceConnected();
		}

		Track getTrackAtIndex(int position);
		int getItemCount();
		Void playQueue(int position);
		void addToQueue(int position);
		void bindService();
		void unbindService();
		void update();
		void close();
		void setOnServiceConnectedListener(OnServiceConnectedListener listener);
	}

	interface Presenter {
		void bindViewHolder(TrackListViewHolder holder, int position);
		int getItemCount();
		void onHolderClick(int position);
		void onAddToPlaylist(int position);
		void onCreate();
		void onDestroy();
	}

	interface View {
		void notifyDataChanged();
		void goToNowPlaying();
	}
}
