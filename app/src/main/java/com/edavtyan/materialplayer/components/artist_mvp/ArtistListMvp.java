package com.edavtyan.materialplayer.components.artist_mvp;

import com.edavtyan.materialplayer.components.ListPresenter;

public interface ArtistListMvp {
	interface Presenter extends ListPresenter<ArtistListViewHolder> {
		void onHolderClicked(ArtistListViewHolder holder, int position);
	}

	interface Model {
		int getArtistCount();
		Artist getArtistAtIndex(int position);
		void update();
	}

	interface View {
		void goToArtistDetail(String title);
	}
}
