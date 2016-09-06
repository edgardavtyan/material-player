package com.edavtyan.materialplayer.components.artist_mvp;

import android.graphics.drawable.Drawable;

import com.edavtyan.materialplayer.components.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListMvp;
import com.edavtyan.materialplayer.components.artists.Artist;

public interface ArtistDetailMvp {
	interface Model extends AlbumListMvp.Model {
		Artist getArtist();
	}

	interface View extends AlbumListMvp.View {
		void setArtistInfo(int albumsCount, int tracksCount);
		void setArtistTitle(String title);
		void setArtistImage(Drawable drawable, int fallbackImage);
	}

	interface Presenter extends AlbumListMvp.Presenter, ParallaxHeaderListPresenter {
	}
}
