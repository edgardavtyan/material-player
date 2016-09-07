package com.edavtyan.materialplayer.components.track_all;

import com.edavtyan.materialplayer.lib.mvp.list.ListPresenter;
import com.edavtyan.materialplayer.db.Track;

public interface TrackListMvp {
	interface Model {
		Track getTrackAtIndex(int position);
		int getItemCount();
		void playQueue(int position);
		void addToQueue(int position);
		void bindService();
		void unbindService();
		void update();
		void close();
	}

	interface Presenter extends ListPresenter<TrackListViewHolder> {
		void onHolderClick(int position);
		void onAddToPlaylist(int position);
	}

	interface View {
		void goToNowPlaying();
	}
}
