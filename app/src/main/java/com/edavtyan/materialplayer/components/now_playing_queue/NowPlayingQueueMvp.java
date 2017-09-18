package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.components.lists.lib.ListMvp;
import com.edavtyan.materialplayer.db.Track;

@SuppressWarnings("unused")
public interface NowPlayingQueueMvp {
	interface Model extends ListMvp.Model {
		interface OnNewTrackListener {
			void onNewTrack();
		}

		void setOnNewTrackListener(OnNewTrackListener listener);

		void bindService();
		void unbindService();
		void playItemAtPosition(int position);
		void removeItemAtPosition(int position);
		Track getTrackAtPosition(int position);
		Track getNowPlayingTrack();
		int getTrackCount();
	}

	interface View extends ListMvp.View {
		void removeItem(int position);
	}

	interface Presenter extends ListMvp.Presenter<NowPlayingQueueViewHolder> {
		void onCreate();
		void onDestroy();
		void onItemClick(int position);
		void onRemoveItemClick(int position);
		int getItemCount();
		int getItemId(int position);
	}
}
