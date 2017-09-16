package com.edavtyan.materialplayer.components.detail.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.lists.track_list.TrackListMvp;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListPresenter;

@SuppressWarnings("unused")
public interface AlbumDetailMvp {
	String EXTRA_ALBUM_ID = "extra_albumId";

	interface Model extends TrackListMvp.Model {
		Album getAlbum();
		Bitmap getAlbumArt();
		long getTotalAlbumDuration();
	}

	interface View extends TrackListMvp.View {
		void setAlbumTitle(String albumTitle);
		void setAlbumInfo(String artistTitle, int tracksCount, long duration);
		void setAlbumImage(Bitmap art);
	}

	interface Presenter extends TrackListMvp.Presenter, ParallaxHeaderListPresenter {
	}
}
