package com.edavtyan.materialplayer.components.artist_mvp;

import com.edavtyan.materialplayer.components.artists.Artist;

public interface ArtistListMvp {
	interface Presenter {
		void bindViewHolder(ArtistListViewHolder holder, int position);
		int getItemCount();
		void onHolderClicked(ArtistListViewHolder holder, int position);
	}

	interface Model {
		int getArtistCount();
		Artist getArtistAtIndex(int position);
	}

	interface View {
		void goToArtistDetail(String title);
	}
}
