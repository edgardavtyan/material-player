package com.edavtyan.materialplayer.components.track_mvp;

public interface TrackListMvp {
	interface Presenter {
		void bindViewHolder(TrackListViewHolder holder, int position);
		int getItemCount();
		void onHolderClick(int position);
		void onAddToPlaylist(int position);
	}
}
