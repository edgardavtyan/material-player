package com.edavtyan.materialplayer.components.album_detail;

import com.edavtyan.materialplayer.components.track_all.TrackListMvp;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListPresenter;

@SuppressWarnings("unused")
public interface AlbumDetailMvp {
	interface Model extends TrackListMvp.Model {
		Album getAlbum();
		String getAlbumArt();
	}

	interface View extends TrackListMvp.View {
		void setAlbumTitle(String albumTitle);
		void setAlbumInfo(String artistTitle, int tracksCount);
		void setAlbumImage(String artPath);
	}

	interface Presenter extends TrackListMvp.Presenter, ParallaxHeaderListPresenter {
	}
}
