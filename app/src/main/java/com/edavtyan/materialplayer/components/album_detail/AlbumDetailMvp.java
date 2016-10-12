package com.edavtyan.materialplayer.components.album_detail;

import com.edavtyan.materialplayer.components.track_all.TrackListMvp;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListPresenter;

import java.io.File;

public interface AlbumDetailMvp {
	interface Model extends TrackListMvp.Model {
		Album getAlbum();
		File getAlbumArt();
	}

	interface View extends TrackListMvp.View {
		void setAlbumTitle(String albumTitle);
		void setAlbumInfo(String artistTitle, int tracksCount);
		void setAlbumImage(File artFile, int fallbackArt);
	}

	interface Presenter extends TrackListMvp.Presenter, ParallaxHeaderListPresenter {
	}
}
