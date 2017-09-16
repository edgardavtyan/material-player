package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.lists.album_list.AlbumListMvp;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListPresenter;

@SuppressWarnings("unused")
public interface ArtistDetailMvp {
	String EXTRA_ARTIST_TITLE = "extra_artistTitle";

	interface Model extends AlbumListMvp.Model {
		Artist getArtist();
		void loadArtistImage(ArtistDetailImageTask.OnImageLoadedCallback callback);
	}

	interface View extends AlbumListMvp.View {
		void setArtistInfo(int albumsCount, int tracksCount);
		void setArtistTitle(String title);
		void setArtistImage(Bitmap art);
	}

	interface Presenter extends AlbumListMvp.Presenter, ParallaxHeaderListPresenter {
	}
}
