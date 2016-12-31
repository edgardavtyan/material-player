package com.edavtyan.materialplayer.components.track_all;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.mvp.list.ListPresenter;

@SuppressWarnings("unused")
public interface TrackListMvp {
	interface Model {
		interface OnCompactModeChangedListener {
			void onCompactModeChanged(boolean isCompactListsEnabled);
		}

		void setOnCompactModeChangedListener(OnCompactModeChangedListener listener);

		boolean isCompactModeEnabled();
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
		boolean isCompactModeEnabled();
	}

	interface View {
		void goToNowPlaying();
		void notifyDataSetChanged();
	}
}
