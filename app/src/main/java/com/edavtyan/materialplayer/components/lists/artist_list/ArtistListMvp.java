package com.edavtyan.materialplayer.components.lists.artist_list;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.components.lists.lib.ListMvp;

@SuppressWarnings("unused")
public interface ArtistListMvp {
	interface Presenter extends ListMvp.Presenter<ArtistListViewHolder> {
		void onHolderClick(int position);
		void onAddToPlaylist(int position);
	}

	interface Model extends ListMvp.Model {
		void addToPlaylist(int artistId);
		int getArtistCount();
		Artist getArtistAtIndex(int position);
		void getArtistImage(int position, ArtistListImageTask.Callback callback);
		void update();
		void bindService();
		void unbindService();
	}

	interface View extends ListMvp.View {
		void gotoArtistDetail(String title);
	}
}
