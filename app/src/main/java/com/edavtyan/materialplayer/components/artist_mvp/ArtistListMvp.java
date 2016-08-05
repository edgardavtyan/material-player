package com.edavtyan.materialplayer.components.artist_mvp;

public interface ArtistListMvp {
	interface Presenter {
		void bindViewHolder(ArtistsListViewHolder holder, int position);
		int getItemCount();
		void onHolderClicked(int position);
	}
}
