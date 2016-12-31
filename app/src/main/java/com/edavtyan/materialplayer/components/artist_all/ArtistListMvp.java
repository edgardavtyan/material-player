package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.mvp.list.ListPresenter;

@SuppressWarnings("unused")
public interface ArtistListMvp {
	interface Presenter extends ListPresenter<ArtistListViewHolder> {
		void onHolderClick(int position);
		boolean isCompactModeEnabled();
	}

	interface Model {
		interface OnCompactListsPrefChangedListener {
			void onCompactListsPrefChanged(boolean isCompactListsEnabled);
		}

		void setOnCompactListsPrefChangedListener(OnCompactListsPrefChangedListener listener);
		int getArtistCount();
		Artist getArtistAtIndex(int position);
		boolean isCompactListsEnabled();
		void update();
	}

	interface View {
		void goToArtistDetail(String title);
		void notifyDataSetChanged();
	}
}
