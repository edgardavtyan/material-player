package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.mvp.list.ListMvp;
import com.edavtyan.materialplayer.lib.mvp.list.ListPresenter;

@SuppressWarnings("unused")
public interface ArtistListMvp {
	interface Presenter extends ListPresenter<ArtistListViewHolder> {
		void onHolderClick(int position);
		boolean isCompactModeEnabled();
	}

	interface Model extends ListMvp.Model {
		int getArtistCount();
		Artist getArtistAtIndex(int position);
		void update();
	}

	interface View {
		void goToArtistDetail(String title);
		void notifyDataSetChanged();
	}
}
