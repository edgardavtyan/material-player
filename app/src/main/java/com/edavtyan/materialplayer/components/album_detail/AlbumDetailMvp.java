package com.edavtyan.materialplayer.components.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.track_all.TrackListMvp;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListPresenter;

@SuppressWarnings("unused")
public interface AlbumDetailMvp {
	String EXTRA_ALBUM_ID = "extra_albumId";

	interface Model extends TrackListMvp.Model {
		Album getAlbum();
		Bitmap getAlbumArt();
	}

	interface View extends TrackListMvp.View {
		void setAlbumTitle(String albumTitle);
		void setAlbumInfo(String artistTitle, int tracksCount);
		void setAlbumImage(Bitmap art);
	}

	interface Presenter extends TrackListMvp.Presenter, ParallaxHeaderListPresenter {
	}
}
