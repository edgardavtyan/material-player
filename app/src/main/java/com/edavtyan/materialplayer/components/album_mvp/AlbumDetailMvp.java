package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.components.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.components.track_mvp.TrackListMvp;
import com.edavtyan.materialplayer.components.track_mvp.Track;

import java.io.File;

public interface AlbumDetailMvp {
	interface Model extends TrackListMvp.Model {
		Album getAlbum();
		Track getFirstAlbumTrack();
	}

	interface View extends TrackListMvp.View {
		void setAlbumTitle(String albumTitle);
		void setAlbumInfo(String artistTitle, int tracksCount);
		void setAlbumImage(File artFile, int fallbackArt);
	}

	interface Presenter extends TrackListMvp.Presenter, ParallaxHeaderListPresenter {
	}
}
