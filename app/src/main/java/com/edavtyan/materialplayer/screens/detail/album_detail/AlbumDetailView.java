package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.screens.lists.track_list.TrackListView;

public interface AlbumDetailView extends TrackListView {
	String EXTRA_ALBUM_ID = "extra_albumId";

	void setAlbumTitle(String title);
	void setAlbumInfo(String artistTitle, int tracksCount, long duration);
	void setAlbumImage(Bitmap albumArt);
}
