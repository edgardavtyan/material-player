package com.edavtyan.materialplayer.components.artist_mvp;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListMvp;

public interface ArtistDetailMvp {
	interface Model extends AlbumListMvp.Model {
		Artist getArtist();
	}

	interface View extends AlbumListMvp.View {
		void setArtistInfo(int albumsCount, int tracksCount);
		void setArtistTitle(String title);
		void setArtistImage(Bitmap art, int fallbackImage);
	}

	interface Presenter extends AlbumListMvp.Presenter, ParallaxHeaderListPresenter {
	}
}
