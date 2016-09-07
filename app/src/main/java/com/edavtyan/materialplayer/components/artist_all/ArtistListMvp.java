package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.lib.mvp.list.ListPresenter;
import com.edavtyan.materialplayer.db.Artist;

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
