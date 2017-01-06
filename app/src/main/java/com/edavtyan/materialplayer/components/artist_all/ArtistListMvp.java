package com.edavtyan.materialplayer.components.artist_all;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.mvp.list.ListMvp;

@SuppressWarnings("unused")
public interface ArtistListMvp {
	interface Presenter extends ListMvp.Presenter<ArtistListViewHolder> {
		void onHolderClick(int position);
	}

	interface Model extends ListMvp.Model {
		int getArtistCount();
		Artist getArtistAtIndex(int position);
		Bitmap getArtistImageFromCache(int position);
		void getArtistImage(int position, ArtistListImageTask.Callback callback);
		void update();
	}

	interface View extends ListMvp.View {
		void goToArtistDetail(String title);
	}
}
